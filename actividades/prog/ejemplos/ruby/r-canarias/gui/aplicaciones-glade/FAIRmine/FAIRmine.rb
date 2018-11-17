require 'const_methods'

class Game

	@fenster; @table; @vbox; @start_button; @hs
	@image; @actual_face
	
	@size_x; @size_y; @old_size_x; @old_size_y; @mines; @mine; @field; @closed_fields
	@free_flag_image; @free_question_flag_image
	@used_flags;
	@game_status
	
	@left_pressed
	
	#OPTIONS
	@endless_time; @cheats; @color
	
	def initialize
		init_options
		
		@hs=HSeparator.new
		@old_size_x=0
		@old_size_y=0
		@mine=[]
		
		@max_time=spielzeit(@size_x,@size_y,@mines)
		@time_multiplicator=1.0
		
		
		@fenster=FAIRwindow.new
		@fenster.resizable=false
		@fenster.set_title("FAIRmine")
		
		@table=Table.new(@size_y,@size_x,true)
		@table.set_border_width(10)
		
		@clock_label=Label.new
		@progress_bar=ProgressBar.new
		@progress_bar.orientation=1 #RIGHT_TO_LEFT
		@progress_bar.modify_bg(STATE_NORMAL,Stock_color)
		
		hbox=HBox.new(false,5)
		hbox.pack_start(@clock_label)
		hbox.pack_start(@progress_bar)
		
		new_start_button #Creates Button with Smile-picture
		new_game
		
		
		@vbox=VBox.new
		@vbox.pack_start(new_menubar)
		@vbox.pack_start(@table)
		@vbox.pack_start(HSeparator.new)
		@vbox.pack_start(HSeparator.new)
		
		@vbox.pack_start(hbox)
		
		@fenster.add(@vbox)
		@fenster.show_all
		
		@fenster.signal_connect("destroy") {quit}
		@fenster.signal_connect("key-press-event") { |w,e| key_pressed(e)}
	end
	
	def init_images #initializes images
		@image=[]
		@image[1]=Image.new(Face_smile)
		@image[2]=Image.new(Face_cool)
		@image[3]=Image.new(Face_win)
		@image[4]=Image.new(Face_sad)
		@image[5]=Image.new(Face_worried)
	end
	
	def new_start_button #Button with Smile
		init_images
		@actual_face=@image[Smile]
		
		@start_button=Button.new
		@start_button.add(@actual_face)
		@start_button.signal_connect("pressed") {
			@start_button.remove(@actual_face)
			
			if @game_status==PLAYING
				@actual_face=@image[Sad]
			else @actual_face=@image[Worried] end
			
			@start_button.add(@actual_face)
			@start_button.show_all
		}
		@start_button.signal_connect("released") {
			if @game_status >= CHANGED
				restart_game
			else
				@start_button.remove(@actual_face)
				@actual_face=@image[Smile]
				
				@start_button.add(@actual_face)
			end
			
			@game_status=NOTHING
			@start_button.show_all
		}
	end
	
	def new_game #initializes buttons...
		@table.remove(@start_button) if !@first_time
		for x in 1..@old_size_x do
			for y in 1..@old_size_y do
				@table.remove(@field[x][y])
			end
		end
		restart_game
		@table.remove(@hs) if !@first_time
		@first_time = false
		
		@table.resize(@size_y,@size_x)
		@table.attach(@start_button,(@size_x/2)-1,(@size_x/2)+1,1,3)
		@table.attach(@hs,0,@size_x,4,5)
		
		@field=[]
		if @size_x > @size_y then
			for i in 1..@size_x do @field[i]=[] end
		else for i in 1..@size_y do @field[i]=[] end
		end
		
		for x in 1..@size_x do
			for y in 1..@size_y do
				@field[x][y]=Field.new
				@field[x][y].set_x_y(x,y)
				@field[x][y].signal_connect("button_press_event") {
					|widget,event|
					field_pressed(widget,event)
				}
				@field[x][y].signal_connect("released") {
					|widget|
					field_released(widget)
				}
				
				
				@table.attach(@field[x][y],x-1,x,y+4,y+5)
				#set colors
				@field[x][y].modify_bg(STATE_NORMAL,@color[1])
				@field[x][y].modify_bg(STATE_PRELIGHT,@color[1])
			end
		end
		@fenster.show_all
		
		@old_size_x=@size_x
		@old_size_y=@size_y
	end
	
	def restart_game #removes images from table; refreshes colors; sets game values...
		@game_status=NOTHING
		@closed_fields=@size_x*@size_y
		
		@start_button.remove(@actual_face)
		@actual_face=@image[Smile]
		@start_button.add(@actual_face)
		
		@clock_label.label="00:00"
		@progress_bar.fraction=0
		
		@cheated=false
		
		#REMOVE PICTURES FROM BUTTONS
		for x in 1..@old_size_x
			for y in 1..@old_size_y
				remove_pic(@field[x][y])
				
				@field[x][y].usr.attribute=F_nil
				@field[x][y].cpu.attribute=F_nil
				
				@field[x][y].modify_bg(STATE_NORMAL,@color[1])
				@field[x][y].modify_bg(STATE_PRELIGHT,@color[1])
				@field[x][y].opened=false
			end
		end
		
		@used_flags=0
	end
	
	def field_pressed(w,e)
	  if @game_status!=FINISHED
		@game_status=CHANGED if @game_status == NOTHING
		
		flag_removed=false
		question_flag_removed=false
		
		if w.usr.attribute != F_hint and w.usr.attribute != F_empty
			@last_action_at=Time.now.to_f
		end
		
		if w.usr.attribute==F_flag #remove flag-question image
				remove_pic(w)
				flag_removed=true
		elsif w.usr.attribute==F_flag_question #remove flag image
				remove_pic(w)
				question_flag_removed=true
		elsif w.usr.attribute==F_hint
			if count_around_field(w.x,w.y,F_flag)==w.hint
				@last_action_at=Time.now.to_f
				simulate_clicks(w.x,w.y)
			end
		end
		
		#CHECK PRESSED BUTTON
		if e.button==1 #LEFT BUTTON
			if !question_flag_removed and !flag_removed then
			if w.usr.attribute==F_nil
				@left_pressed=true
				w.modify_bg(STATE_NORMAL,@color[2])
				w.modify_bg(STATE_PRELIGHT,@color[2])
			end
			end
		elsif e.button==2 #MIDDLE BUTTON
			if @left_pressed
				w.modify_bg(STATE_NORMAL,@color[1])
				w.modify_bg(STATE_PRELIGHT,@color[1])
				@left_pressed=false
			else
			
			if !question_flag_removed then
				if w.usr.attribute==F_nil
					add_pic(w,F_flag_question)
				end
			end
			end
		elsif e.button==3 #RIGHT BUTTON
			if @left_pressed
				w.modify_bg(STATE_NORMAL,@color[1])
				w.modify_bg(STATE_PRELIGHT,@color[1])
				@left_pressed=false
			else
			
			if !flag_removed then
				if w.usr.attribute==F_nil
					if @used_flags < @mines
						add_pic(w,F_flag)
					else
						Gdk.beep
					end
				end
			end
			end
		end
		@fenster.show_all
	  else
		if w.usr.attribute==F_empty or w.usr.attribute==F_hint
			w.modify_bg(STATE_NORMAL,@color[3])
		else
			w.modify_bg(STATE_NORMAL,@color[1])
		end
	  end
	end
	
	def simulate_clicks(x,y)
		clicked_on_field(x-1,y-1)
		clicked_on_field(x,y-1)
		clicked_on_field(x+1,y-1)
		clicked_on_field(x+1,y)
		clicked_on_field(x+1,y+1)
		clicked_on_field(x,y+1)
		clicked_on_field(x-1,y+1)
		clicked_on_field(x-1,y)
	end
	
	def field_released(w)
		x=w.x
		y=w.y
		if @left_pressed
			if @game_status != PLAYING then #first time on button clicked...
				@start_button.remove(@actual_face)
				@actual_face=@image[Cool]
				@start_button.add(@actual_face)
				
				@game_status=PLAYING
				
				w.cpu.attribute=F_empty
				set_field_attr(x-1,y-1,F_empty) #Dieser Block
				set_field_attr(x,y-1,F_empty)   #bewirkt,
				set_field_attr(x+1,y-1,F_empty) #dass sich am
				set_field_attr(x+1,y,F_empty)   #Beginn des
				set_field_attr(x+1,y+1,F_empty) #Spiels
				set_field_attr(x,y+1,F_empty)   #mindestens
				set_field_attr(x-1,y+1,F_empty) #neun (3*3)
				set_field_attr(x-1,y,F_empty)   #Felder öffnen...
				
				put_mines
				open_fields(w)
				
				start_game_clock
			end
			
			clicked_on_field(x,y)
			
			@left_pressed=false
		end
		@fenster.show_all
	end
	
	def clicked_on_field(x,y)
		if x!=0 and y!=0 and x!=@size_x+1 and y!=@size_y+1
			if @field[x][y].cpu.attribute==F_mine and @field[x][y].usr.attribute!=F_flag #DEAD...
				lose(@field[x][y])
			elsif @field[x][y].usr.attribute==F_nil or @field[x][y].usr.attribute==F_flag_question
					open_fields(@field[x][y])
			end
		end
	end
	
	def put_mines #puts mines on the Game-field
		rand_x=0
		rand_y=0
		i=0
		
		@mines.times do
			i+=1
			begin
				rand_x=((rand*@size_x)+1).to_i
				rand_y=((rand*@size_y)+1).to_i
			end until @field[rand_x][rand_y].cpu.attribute==F_nil
			@mine[i]=Mine.new
			@mine[i].set_x_y(rand_x,rand_y)
			
			@field[rand_x][rand_y].cpu.attribute=F_mine
		end
	end
	
	def set_field_attr(x,y,attr,wich="cpu")
		if x!=0 and y!=0 and x!=@size_x+1 and y!=@size_y+1
			if wich=="usr"
				@field[x][y].usr.attribute=attr
			else
				@field[x][y].cpu.attribute=attr
			end
		end
	end
	
	def count_around_field(x,y,attr) #counts how much buttons with »attr« attribute are around a field
		anzahl=0
	   if @field[x][y].cpu.attribute != attr
		if x!=1 and y!=1 #links-oben
			if @field[x-1][y-1].cpu.attribute==attr or @field[x-1][y-1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if y!=1 #mitte-links
			if @field[x][y-1].cpu.attribute==attr or @field[x][y-1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if x!=@size_x and y!=1 #rechts-oben
			if @field[x+1][y-1].cpu.attribute==attr or @field[x+1][y-1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if x!=@size_x #rechts-mitte
			if @field[x+1][y].cpu.attribute==attr or @field[x+1][y].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if x!=@size_x and y!=@size_y #rechts-unten
			if @field[x+1][y+1].cpu.attribute==attr or @field[x+1][y+1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if y!=@size_y #mitte-unten
			if @field[x][y+1].cpu.attribute==attr or @field[x][y+1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if x!=1 and y!=@size_y #links-unten
			if @field[x-1][y+1].cpu.attribute==attr or @field[x-1][y+1].usr.attribute==attr
				anzahl+=1
			end
		end
		
		if x!=1 #links-mitte
			if @field[x-1][y].cpu.attribute==attr or @field[x-1][y].usr.attribute==attr
				anzahl+=1
			end
		end
	   end
		return anzahl
	end
	
	def remove_pic(w)
		if w.children.length != 0
			w.remove(w.children[0])
			if w.usr.attribute==F_flag
				@used_flags -= 1
			end
			w.usr.attribute=F_nil
		end
	end
	
	def add_pic(w,attr)
		if attr==F_flag
			w.add(Image.new(Flag_flag))
			@used_flags+=1
		elsif attr==F_flag_question
			w.add(Image.new(Flag_question))
		end
		
		w.usr. attribute=attr
	end
	
	
	#FIELD OPENING METHODS
	def open_fields(w) #MAIN OPENING
		@to_open_x=[]
		@to_open_y=[]
		
		open_field(w.x,w.y)
		anzahl=count_around_field(w.x,w.y,F_mine)
		if anzahl == 0
			@to_open_x << w.x
			@to_open_y << w.y
			i=0
			begin
				open_around_field(@to_open_x[i],@to_open_y[i])
				i+=1
			end until i >= @to_open_x.length
		end
	end
	
	def open_around_field(x,y) #opens every field around an empty field
		open_field(x-1,y-1) if x!=1 and y!=1
		open_field(x,y-1) if y!=1
		open_field(x+1,y-1) if x!=@size_x and y!=1
		open_field(x+1,y) if x!=@size_x
		open_field(x+1,y+1) if x!=@size_x and y!=@size_y
		open_field(x,y+1) if y!=@size_y
		open_field(x-1,y+1) if x!=1 and y!=@size_y
		open_field(x-1,y) if x!=1
	end
	
	def open_field(x,y) #opens only one field
	   if !@field[x][y].opened
		remove_pic(@field[x][y])
		
		anzahl=count_around_field(x,y,F_mine)
		if anzahl > 0
			@field[x][y].add(Image.new(NR[anzahl]))
			
			@field[x][y].usr.attribute=F_hint
			@field[x][y].hint=anzahl
		else
			@to_open_x << x
			@to_open_y << y
			
			@field[x][y].usr.attribute=F_empty
		end
		
		@field[x][y].modify_bg(STATE_NORMAL,@color[3])
		@field[x][y].modify_bg(STATE_PRELIGHT,@color[3])
		@field[x][y].opened=true
		
		@closed_fields -= 1
		win if @closed_fields == @mines
	   end
	end
	
	def start_game_clock(s_t=Time.now.to_f)
		@start_time=s_t
		Thread.start {
			begin
				@actual_time=Time.now.to_f
				if !@endless_time
					@clock_label.label=time_in_string(@max_time - (@actual_time - @start_time))
					
					fortschritt=100 / @max_time.to_f * (@max_time.to_f - (@actual_time - @start_time))
					@progress_bar.fraction=fortschritt/100
				else
					@clock_label.label=time_in_string(@actual_time - @start_time)
				end
				
				if @actual_time - @last_action_at >= 5 #5 Sekunden nichts gemacht...
					if !@start_button_changed
						@start_button_changed=true
						
						@start_button.remove(@actual_face)
						@actual_face=@image[Worried]
						@start_button.add(@actual_face)
						
						@fenster.show_all
					end
				else
					if @start_button_changed
						@start_button_changed=false
						
						@start_button.remove(@actual_face)
						@actual_face=@image[Cool]
						@start_button.add(@actual_face)
						
						@fenster.show_all
					end
				end
				
				if !@endless_time
					if (@actual_time - @start_time) >= @max_time
						loose
						@fenster.show_all
						@progress_bar.fraction=0
					end
				end
			end until @game_status==FINISHED or @game_status==NOTHING
			
			if @game_status==NOTHING
				@clock_label.label="00:00"
				@progress_bar.fraction=0
			end
		}
	end
	#END OF GAME METHODS
	
	
	
	#GAME ENDING METHODS
	def win
		for i in 1..@mines do
			x=@mine[i].x
			y=@mine[i].y
			
			remove_pic(@field[x][y])
			add_pic(@field[x][y],F_flag)
		end
		
		@start_button.remove(@actual_face)
		@actual_face=@image[Win]
		@start_button.add(@actual_face)
		
		@game_status=FINISHED
		
		@fenster.show_all
		if @closed_fields==@mines and !@endless_time #won
			prozent=(@progress_bar.fraction * 100) / @time_multiplicator
			
			new_highscore(@max_time - (@actual_time - @start_time),prozent)
		end
	end
	
	def new_highscore(t,p)
		h=[]
		4.times { | i | h[i]=[]}
		
		msg="New Top-10 highscore!!\nPlease enter your name: "
		
		dif=actual_difficulty
		dif-=1
		if File.exists?("#{SAVE_PATH}highscores.sav")
			datei=File.new("#{SAVE_PATH}highscores.sav","r")
			h=Marshal.load(datei)
			datei.close
			
			greater=-1
			(h[dif].length - 1).downto(0) { | i |
				if p > h[dif][i].percent
					greater=i
				end
			}
			
			if greater==-1
				if h[dif].length < 10
					dialog=MessageDialog.new(@fenster,0,0,1,msg)
					dialog.set_icon(Gdk::Pixbuf.new(Logo))
					dialog.vbox.pack_start(entry=Entry.new).show_all
					entry.text=@default_name
					
					dialog.run
					
					n=entry.text
					@default_name = entry.text
					dialog.destroy
					
					
					n="*#{n}*" if @cheated
					h[dif] << Highscore.new(n,t,runde_auf_kommastellen(p,2))
				else
					dialog=MessageDialog.new(@fenster,0,0,1,"No highscore... You were late!")
					dialog.set_icon(Gdk::Pixbuf.new(Logo))
					dialog.run
					dialog.destroy
				end
			else
				#HIGHSCORE MITTEN DRIN...
				dialog=MessageDialog.new(@fenster,0,0,1,msg)
				dialog.set_icon(Gdk::Pixbuf.new(Logo))
				dialog.vbox.pack_start(entry=Entry.new).show_all
				entry.text=@default_name
				
				dialog.run
				
				n=entry.text
				@default_name = entry.text
				dialog.destroy
				
				
				n="*#{n}*" if @cheated
				h[dif].insert(greater,Highscore.new(n,t,runde_auf_kommastellen(p,2)))
				
				if h[dif].length > 10
					h[dif].delete_at(10)
					#only 10 highscores are possible...
				end
			end
		else
			dialog=MessageDialog.new(@fenster,0,0,1,msg)
			dialog.set_icon(Gdk::Pixbuf.new(Logo))
			dialog.vbox.pack_start(entry=Entry.new).show_all
			entry.text=@default_name
			
			dialog.run
			
			n=entry.text
			@default_name = entry.text
			dialog.destroy
			
			
			n="*#{n}*" if @cheated
			h[dif][0]=Highscore.new(n,t,runde_auf_kommastellen(p,2))
		end
		
		datei=File.new("#{SAVE_PATH}highscores.sav","w")
		Marshal.dump(h,datei)
		datei.close
		
		Bestenliste.new
	end
	
	
	
	
	def lose(w=nil)
		
		for i in 1..@mines do
			x=@mine[i].x
			y=@mine[i].y
			
			remove_pic(@field[x][y])
			
			@field[x][y].usr.attribute=F_mine
			@field[x][y].add(Image.new(Mine_mine))
		end
		
		if w != nil
			remove_pic(w)
			w.usr.attribute=F_exploded_mine
			w.add(Image.new(Mine_exploded))
			w.modify_bg(STATE_NORMAL,@color[1])
			w.modify_bg(STATE_PRELIGHT,@color[1])
		end
		
		@start_button.remove(@actual_face)
		@actual_face=@image[Sad]
		@start_button.add(@actual_face)
		
		@game_status=FINISHED
	end
	
	
	#CHEATING...
	def key_pressed(e)
		if e.keyval < 256
			if @cheating
				if e.keyval.chr != CHEATS[@actual_cheat][@letter] or
				Time.now.to_f - @last_key_pressed_at > CHEAT_TIME #wrong key pressed or too late...
					@cheating=false
				end
			end
			
			if !@cheating #first time...
				@actual_cheat=-1
				
				@cheating=true
				
				@last_key_pressed_at=Time.now.to_f
				@letter=0
				
				CHEATS.length.times  { | i |
					@actual_cheat=i if CHEATS[i][0]==e.keyval.chr
				}
			end
			
		   if @actual_cheat != -1 #if button is in any cheat...
			if Time.now.to_f - @last_key_pressed_at <= CHEAT_TIME
				@last_key_pressed_at=Time.now.to_f
				
				@letter += 1
				if CHEATS[@actual_cheat][@letter]=="|"
					cheat!(@actual_cheat)
				end
			end
		   end
		else
			return -1
		end
	end
	
	def cheat!(anzahl) #Cheat wird ausgeführt!
		if anzahl < 5
			@cheated=true
		end
		
		case anzahl
			when 0 #»mines«
				if @game_status == PLAYING #wenn man spielt...
					for i in 1..@mines do #Die Farbe jedes "Minen"-feldes ändern...
						@field[@mine[i].x][@mine[i].y].modify_bg(STATE_PRELIGHT,@color[3])
					end
				end
			when 1 #»time«
				if @game_status == PLAYING and !@endless_time #wenn man spielt (auf Zeit)...
					if @max_time - (Time.now.to_f - @start_time) + 10 > @max_time
						@start_time=Time.now.to_f
					else
						@start_time += 10 #10 sec. mehr Zeit
					end
				end
			when 2 #»hint«
				if @game_status == PLAYING
					for i in 1..@mines
						w=@field[@mine[i].x][@mine[i].y]
						if w.usr.attribute != F_flag
							remove_pic(w)
							add_pic(w,F_flag)
							break
						end
						i+=1
					end
					@fenster.show_all
				end
			when 3 #»seethemines«   "discovers" all mines...
				if @game_status == PLAYING
					for i in 1..@mines
						w=@field[@mine[i].x][@mine[i].y]
						if w.usr.attribute != F_flag
							remove_pic(w)
							add_pic(w,F_flag)
						end
					end
					@fenster.show_all
				end
			when 4 #»iamthewinner«   wins the game...
				if @game_status == PLAYING
					for x in 1..@size_x
						for y in 1..@size_y
							if @field[x][y].cpu.attribute != F_mine
								open_field(x,y)
							end
						end
					end
				end
				@fenster.show_all
			when 5 #»+«
				if @color[1].red < 40536 and @color[1].green < 40536 and @color[1].blue < 40536
					r=@color[1].red + 5000
					g=@color[1].green + 5000
					b=@color[1].blue + 5000
					@color[1]=Gdk::Color.new(r,g,b)
					make_colors
					
					for x in 1..@size_x
						for y in 1..@size_y
							c=@color[1]
							if @field[x][y].usr.attribute==F_empty or @field[x][y].usr.attribute==F_hint
								c=@color[3]
							end
							
							@field[x][y].modify_bg(STATE_NORMAL,c)
							@field[x][y].modify_bg(STATE_PRELIGHT,c)
						end
					end
				end
			when 6 #»-«
				if @color[1].red > 5000 and @color[1].green > 5000 and @color[1].blue > 5000
					r=@color[1].red - 5000
					g=@color[1].green - 5000
					b=@color[1].blue - 5000
					@color[1]=Gdk::Color.new(r,g,b)
					make_colors
					
					for x in 1..@size_x
						for y in 1..@size_y
							c=@color[1]
							if @field[x][y].usr.attribute==F_empty or @field[x][y].usr.attribute==F_hint
								c=@color[3]
							end
							
							@field[x][y].modify_bg(STATE_NORMAL,c)
							@field[x][y].modify_bg(STATE_PRELIGHT,c)
						end
					end
				end
		end
	end
	
	
	#MENU BAR
	def new_menubar
		accel_group=AccelGroup.new
		@fenster.add_accel_group(accel_group)
		
		item_factory=ItemFactory.new(ItemFactory::TYPE_MENU_BAR,'<main>',accel_group)
		
		menu_items = [
		  #SPIEL
		  ["/_Game"],
		  ["/_Game/tearoff1",'<Tearoff>',"1"],
		  ["/_Game/_New",ItemFactory::IMAGE_ITEM,"F2",
		  Gdk::Pixbuf.new(Logo,15,15),Proc.new{on_menu_activate("new")}],
		  
		  ["/_Game/_Highscores",ItemFactory::STOCK_ITEM,"<control>B",
		  Stock::JUSTIFY_FILL,Proc.new{on_menu_activate("highscores")}],
		  
		  ["/_Game/sep1",'<Separator>'],
		  ["/_Game/_Quit",ItemFactory::STOCK_ITEM,"<control>Q",
		  Stock::QUIT,Proc.new{on_menu_activate("quit")}],
		  
		  #EINSTELLUNGEN
		  ["/_Settings"],
		  ["/_Settings/tearoff2",'<Tearoff>',"2"],
		  ["/_Settings/_Preferences",ItemFactory::STOCK_ITEM,"F5",
		  Stock::PREFERENCES,Proc.new{on_menu_activate("game_settings")}],
		  
		  
		  ["/_Settings/sep2",'<Separator>'],
		  ["/_Settings/_Color",ItemFactory::STOCK_ITEM,"F6",
		  Stock::SELECT_COLOR,Proc.new{on_menu_activate("color_settings")}],
		  
		  ["/_Settings/Brighter (+)",ItemFactory::STOCK_ITEM,nil,
		  Stock::GO_UP,Proc.new{cheat!(5)}], #vlt.: Stock::YES
		  
		  ["/_Settings/Darker (-)",ItemFactory::STOCK_ITEM,nil,
		  Stock::GO_DOWN,Proc.new{cheat!(6)}], #vlt.: Stock::NO
		  
		  
		  ["/_Settings/sep3",'<Separator>'],
		  ["/_Settings/_Reset",ItemFactory::STOCK_ITEM,"<control>S",
		  Stock::REFRESH,Proc.new{on_menu_activate("stock_settings")}],
		  
		  #?
		  ["/_?"],
		  ["/_?/_Contents",ItemFactory::STOCK_ITEM,"F1",
		  Stock::HELP,Proc.new{on_menu_activate("help")}],
		  
		  ["/_?/_About",ItemFactory::STOCK_ITEM,"<control>I",
		  Stock::DIALOG_INFO,Proc.new{on_menu_activate("info")}]
		]
		
		
		
		if File.exists?("#{SAVE_PATH}highscores.sav")
			datei=File.new("#{SAVE_PATH}highscores.sav","r")
			h=Marshal.load(datei)
			datei.close
			
			if h[0].length > 0 and h[1].length > 0 and h[2].length > 0
				menu_items << ["/cheats","<LastBranch>"]
				menu_items << ["/cheats/mines","<Item>",nil,nil,Proc.new{cheat!(0)}]
				menu_items << ["/cheats/time","<Item>",nil,nil,Proc.new{cheat!(1)}]
				menu_items << ["/cheats/hint","<Item>",nil,nil,Proc.new{cheat!(2)}]
				menu_items << ["/cheats/iseedeadmines","<Item>",nil,nil,Proc.new{cheat!(3)}]
				menu_items << ["/cheats/iamthewinner","<Item>",nil,nil,Proc.new{cheat!(4)}]
			end
		end
		
		
		item_factory.create_items(menu_items)
		
		fac=item_factory.get_widget('<main>')
		fac.modify_bg(STATE_NORMAL,Gdk::Color.new(50000,45000,40000))
		return fac
	end
	
	def on_menu_activate(name)
		case name
			when "new"
				restart_game if @game_status >= CHANGED
			when "highscores"
				restart_game if @game_status==PLAYING
				Bestenliste.new
			when "quit"
				quit
			when "game_settings"
				restart_game if @game_status==PLAYING
				game_settings
			when "color_settings"
				restart_game if @game_status==PLAYING
				color_settings
			when "stock_settings"
			    if !stock_options
				msg="Resetting Settings\nIs that ok?"
				dialog=MessageDialog.new(@fenster,0,2,4,msg)
				dialog.run {|response|
					if response == -8 #Yes_button
						make_stock_settings
						new_game
					end
				}
				dialog.destroy
			    else #when stock_settings..
				    msg="You already have stock settings"
				    dialog=MessageDialog.new(@fenster,0,0,1,msg)
				    dialog.run
				    dialog.destroy
			    end
			when "help"
				restart_game if @game_status==PLAYING
				Help.new
			when "info"
				restart_game if @game_status==PLAYING
				about
		end
	end
	
	
	
	#SETTINGS METHODS
	def color_settings
		cs=ColorSelectionDialog.new("setting color")
		
		cs.set_modal(@fenster)
		cs.modify_bg(STATE_NORMAL,Stock_color)
		cs.set_icon(Gdk::Pixbuf.new(Logo))
		cs.colorsel.has_palette=true
		cs.colorsel.current_color=@color[1]
		
		cs.show_all
		
		##SIGNALS##
		cs.help_button.signal_connect("clicked") {
			msg="Choose a color!\nUse one of the presets,\nor create a new one!"
			dialog=MessageDialog.new(cs,0,0,2,msg)
			dialog.run
			dialog.destroy
		}
		##########
		
		##########
		cs.ok_button.signal_connect("clicked") {
			color=cs.colorsel.current_color
			ok_button_clicked=true
			if color.red > 45535 or color.green > 45535 or color.blue > 45535
				ok_button_clicked=false
				msg="Color too bright!\nValues will be changed." +\
				"\nis that ok?"
				dialog=MessageDialog.new(cs,0,2,4,msg)
				
				dialog.run {|response|
					if response == -8 #Yes_button
						ok_button_clicked=true
						color.red = 45535 if color.red > 45535
						color.green = 45535 if color.green > 45535
						color.blue = 45535 if color.blue > 45535
					end
				}
				dialog.destroy
			end
			
			if ok_button_clicked
				@change_dow_color=false
				@color[1]=color
				make_colors
				restart_game #also 'refreshes' colors
				cs.destroy
			end
		}
		##########
		
		##########
		cs.cancel_button.signal_connect("clicked") {cs.destroy}
		##########
	end
	
	def game_settings
		@on_scrollbar_clicked=false
		@change_values=true
		
		w=FAIRwindow.new
		w.resizable=false
		w.set_border_width(15)
		w.set_modal(@fenster)
		w.set_title("Settings")
		
		#DIFFICULTY
		difficulty_frame=Frame.new("Difficulty")
		difficulty_vbox=VBox.new(true,4)
		difficulty_vbox.set_border_width(5)
		
		@difficulty=[]
		@difficulty[1]=RadioButton.new(Difficulty_text[1])
		difficulty_vbox.pack_start(@difficulty[1])
		for i in 2..4 do
			@difficulty[i]=RadioButton.new(@difficulty[1],Difficulty_text[i])
			difficulty_vbox.pack_start(@difficulty[i])
		end
		difficulty_frame.add(difficulty_vbox)
		#end of difficulty
		
		#VALUE
		value_frame=Frame.new("Values")
		value_vbox=VBox.new(true,4)
		value_vbox.set_border_width(5)
		
		value_hbox=[]
		@spin_button=[]
		for i in 1..3 do
			value_hbox[i]=HBox.new(true,2)
			
			@spin_button[i]=SpinButton.new
			@spin_button[i].set_size_request(40,-1)
			@spin_button[i].set_increments(1,1)
			@spin_button[i].numeric=true
			
			value_hbox[i].pack_start(Label.new(Label_text[i]))
			value_hbox[i].pack_start(@spin_button[i])
			
			value_vbox.pack_start(value_hbox[i])
		end
		@spin_button[1].set_range(10,40)
		@spin_button[2].set_range(5,25)
		@spin_button[3].set_range(5,300)
		
		@spin_button[1].set_increments(2,2) #Wert muss gerade sein...
		
		
		value_frame.add(value_vbox)
		#end of value
		
		#add DIFFICULTY and VALUE
		difficulty_and_value=HBox.new(true,10)
		difficulty_and_value.pack_start(difficulty_frame)
		difficulty_and_value.pack_start(value_frame)
		#end of add difficulty and value
		
		#difficulty label...
		@difficulty_label=Label.new
		
		difficulty_hbox=HBox.new(true,10)
		difficulty_hbox.pack_start(Label.new("Difficulty: "))
		difficulty_hbox.pack_start(@difficulty_label)
		#end of difficulty label
		
		#TIME
		@time_frame=Frame.new("Time")
		time_vbox=VBox.new(true,2)
		time_vbox.set_border_width(5)
		
		time_hbox=[]
		time_hbox[1]=HBox.new(true,5)
		time_hbox[2]=HBox.new(true,5)
		
		@time_scrollbar=HScrollbar.new
		@time_scrollbar=HScrollbar.new
		@time_scrollbar.set_range(1,4.99)
		@time_scrollbar.set_increments(1,1)
		
		@time_text_label=Label.new(Time_text[1])
		
		time_hbox[1].pack_start(@time_scrollbar)
		time_hbox[1].pack_start(@time_text_label)
		
		@time_show_time_label=Label.new
		
		time_hbox[2].pack_start(Label.new("Time:"))
		time_hbox[2].pack_start(@time_show_time_label)
		
		time_vbox.pack_start(time_hbox[1])
		time_vbox.pack_start(time_hbox[2])
		@time_frame.add(time_vbox)
		#end of time
		
		#CHECK BUTTONS
		check_button_vbox=VBox.new(true,2)
		
		@check_button_classic=CheckButton.new("Classic endless-game")
		@check_button_change_dow_color=CheckButton.new("Adapt color every day")
		
		check_button_vbox.pack_start(@check_button_classic)
		check_button_vbox.pack_start(@check_button_change_dow_color)
		#end of check buttons
		
		#BUTTONS
		button_hbox=HBox.new(false,5)
		
		button_ok=Button.new(Stock::OK)
		button_abbrechen=Button.new(Stock::CANCEL)
		
		button_hbox.pack_start(button_ok)
		button_hbox.pack_start(button_abbrechen)
		#end of buttons
		
		#EVERYTHING
		everything=VBox.new(false,10)
		everything.pack_start(difficulty_and_value)
		everything.pack_start(difficulty_hbox)
		everything.pack_start(@time_frame)
		everything.pack_start(check_button_vbox)
		everything.pack_start(button_hbox)
		#end of everything
		
		w.add(everything)
		w.show_all
		
		#####INIT VALUES#####
		if actual_difficulty==4 #benutzerdefiniert
			@difficulty[4].active=true
			set_values(@size_x,@size_y,@mines)
		else
			@difficulty[actual_difficulty].active=true
			init_values(actual_difficulty)
		end
		
		
		if @endless_time
			@check_button_classic.active=true
			@time_frame.sensitive=false
		end
		
		@check_button_change_dow_color.active=true if @change_dow_color
		#end of init values
		
		##-------------------------------##
		##########SIGNALS##########
		##-------------------------------##
		
		##########
		@difficulty[1].signal_connect("toggled") {init_values(1)}
		@difficulty[2].signal_connect("toggled") {init_values(2)}
		@difficulty[3].signal_connect("toggled") {init_values(3)}
		@difficulty[4].signal_connect("toggled") {init_values(4)}
		##########
		
		##########
		@spin_button[1].signal_connect("value_changed") {refresh_values}
		@spin_button[2].signal_connect("value_changed") {refresh_values}
		@spin_button[3].signal_connect("value_changed") {refresh_values}
		##########
		
		##########
		@time_scrollbar.signal_connect("value_changed") {show_time}
		##########
		
		##########
		@check_button_classic.signal_connect("toggled") {show_time}
		##########
		
		##########
		button_ok.signal_connect("clicked") {
			transfer_values
			w.destroy
		}
		button_abbrechen.signal_connect("clicked") {w.destroy}
		##########
	end
	
	def init_values(anzahl) #initializes values (if RaioButton is activated)
		if anzahl != 4
			if !@on_scrollbar_clicked
				@spin_button[3].set_range(5,300)
				set_values(Difficulty_size_x[anzahl],Difficulty_size_y[anzahl],Difficulty_mines[anzahl])
			end
			@on_scrollbar_clicked=false
		end
		
		show_time
	end
	
	def refresh_values #updates values (if SpinButton value is changed (by pressing or entering))
	  if @change_values
		if !@difficulty[4].active?
			@on_scrollbar_clicked=true
			@difficulty[4].active=true
		end
		
		show_time
	  end
	end
	
	def set_values(s_x,s_y,m) #sets values from SpinButton (if RadioButton is toggled)
		@change_values=false
		@spin_button[1].value=s_x
		@spin_button[2].value=s_y
		@spin_button[3].value=m
		@change_values=true
		
		show_time
	end
	
	def show_time
		if @check_button_classic.active?
			@time_show_time_label.set_label("---")
			@time_frame.sensitive=false
		else
			@time_frame.sensitive=true
			show_time=spielzeit(@spin_button[1].value,@spin_button[2].value,@spin_button[3].value,@time_scrollbar.value)
			@time_show_time_label.set_label(time_in_string(show_time))
		end
		@time_text_label.set_label(Time_text[@time_scrollbar.value.to_i])
		
		
		
		prozent=100/(@spin_button[1].value*@spin_button[2].value)*@spin_button[3].value
		@difficulty_label.label=check_difficulty(prozent)
		
		c=Gdk::Color.new(65535,0,0)
		case @difficulty_label.label
			when "Easy"
				c=Gdk::Color.new(0,40000,0)
			when "Normal"
				c=Gdk::Color.new(40000,40000,0)
			when "Hard"
				c=Gdk::Color.new(65535,40000,0)
		end
		
		@difficulty_label.modify_fg(STATE_NORMAL,c)
		
		prozent=((@spin_button[1].value*@spin_button[2].value)/100*30).round
		@spin_button[3].set_range(5,prozent)
	end
	
	def transfer_values #sets game values!
		@spin_button[1].value -= 1 if @spin_button[1].value % 2 != 0 #size_x 'MUSS' gerade sein
		@size_x=@spin_button[1].value.to_i
		@size_y=@spin_button[2].value.to_i
		@mines=@spin_button[3].value.to_i
		
		
		@endless_time=false
		@endless_time=true if @check_button_classic.active?
		
		@max_time=spielzeit(@spin_button[1].value,@spin_button[2].value,@spin_button[3].value,@time_scrollbar.value)
		@time_multiplicator=@time_scrollbar.value
		
		@change_dow_color=false
		if @check_button_change_dow_color.active?
			@change_dow_color=true
			@color[1]=DOW_COLOR[Time.now.wday]
			make_colors
		end
		
		new_game
	end
	
	
	
	#OPTIONS METHODS
	def init_options #reads options from data or makes stock settings
		@color=[]
		@first_time = true
		if File.exists?("#{SAVE_PATH}options.sav")
			datei=File.new("#{SAVE_PATH}options.sav","r")
			
			@default_name=datei.gets.chomp
			
			@size_x=datei.gets.to_i
			@size_y=datei.gets.to_i
			@mines=datei.gets.to_i
			
			@color[1]=Gdk::Color.new(datei.gets.to_i,datei.gets.to_i,datei.gets.to_i)
			
			@endless_time=true
			@change_dow_color=true
			
			
			if datei.gets.to_i==0
				@endless_time=false
			end
			
			if datei.gets.to_i==0
				@change_dow_color=false
			else @color[1]=DOW_COLOR[Time.now.wday]
			end
			
			datei.close
		else #STOCK SETTINGS
			make_stock_settings
		end
		
		make_colors
	end
	
	def make_stock_settings
		@default_name="Unknown"
		
		@endless_time=false
		@change_dow_color=true
		
		@size_x=Difficulty_size_x[1]
		@size_y=Difficulty_size_y[1]
		@mines=Difficulty_mines[1]
		
		@color[1]=DOW_COLOR[Time.now.wday]
		make_colors
	end
	
	def quit #saves options and quits
	  if !stock_options
		datei=File.new("#{SAVE_PATH}options.sav","w")
		
		datei.puts @default_name
		
		datei.puts @size_x
		datei.puts @size_y
		datei.puts @mines
		
		datei.puts @color[1].red
		datei.puts @color[1].green
		datei.puts @color[1].blue
		
		
		if @endless_time
			datei.puts 1
		else
			datei.puts 0
		end
		
		if @change_dow_color
			datei.puts 1
		else
			datei.puts 0
		end
		
		datei.close
	  else #if stock_options -> delete data...
		File.delete("#{SAVE_PATH}options.sav") if File.exists?("#{SAVE_PATH}options.sav")
	  end
		
		main_quit
	end
	
	def stock_options #checks, if options are still standard...
		stock=false
		if !@endless_time and @change_dow_color and
		@size_x==12 and @size_y==10 and @mines==12 and @default_name=="Unknown"
			stock=true
		end
		
		
		return stock
	end
	
	def make_colors #makes color[2] and color[3] from color[1]...
		@color[2]=Gdk::Color.new(@color[1].red+10000,@color[1].green+10000,@color[1].blue+10000)
		@color[3]=Gdk::Color.new(@color[1].red+20000,@color[1].green+20000,@color[1].blue+20000)
	end
	
	def actual_difficulty #gets actual difficulty ("einfach","mittel","schwer","benutzerdefiniert")...
		gefunden=false
		for i in 1..3
			if @size_x==Difficulty_size_x[i] and @size_y==Difficulty_size_y[i] and @mines==Difficulty_mines[i]
				gefunden=true
				return i
			end
		end
		
		if !gefunden
			return 4
		end
	end
	
	
	
	
	def about
		ad=AboutDialog.new
		ad.set_modal(true)
		ad.artists=["Faces:\nTuomas Kuosmanen","\nLogo:\nFabian Irsara"]
		ad.authors=["Fabian Irsara   <fabianirsara@hotmail.com>"]
		ad.comments="Thanks to Franz Burgmann"
		ad.copyright="Copyright (C) 2005 Fabian Irsara"
		ad.name="FAIRmine"
		ad.version="1.5"
		ad.website="Critics to: fabianirsara@hotmail.com"
		ad.logo=Gdk::Pixbuf.new(Logo)
		
		ad.set_icon(Gdk::Pixbuf.new(Logo))
		
		
		ad.show_all
	end
end


#MAIN PROGRAM
init
Game.new
main
