require 'gtk2'
require 'ftools' #File tools...
include Gtk

Stock_color=Gdk::Color.new(55000,55000,50000)


#path, where options are being saved...


SAVE_PATH=""


#KEYVAL CONSTANTS
ESC=65307






#DATE CONSTANTS
DOW=[]
DOW[0]="Sunday"
DOW[1]="Monday"
DOW[2]="Tuesday"
DOW[3]="Wednesday"
DOW[4]="Thursday"
DOW[5]="Friday"
DOW[6]="Saturday"

#DATE COLOR CONSTANTS
DOW_COLOR=[]
DOW_COLOR[0]=Gdk::Color.new(27557,33213,35293) #cyan
DOW_COLOR[1]=Gdk::Color.new(39559,45535,20385) #lightgreen
DOW_COLOR[2]=Gdk::Color.new(45535,45535,23155) #lemon
DOW_COLOR[3]=Gdk::Color.new(31596,14236,12304) #darkred
DOW_COLOR[4]=Gdk::Color.new(20480,21335,35986) #violet
DOW_COLOR[5]=Gdk::Color.new(20496,42928,42650) #türkis
DOW_COLOR[6]=Gdk::Color.new(38785,40569,34328) #bright-pink

=begin
	######
	optional Colors...
	DOW_COLOR[10000]=Gdk::Color.new(36895,37412,26117)
	DOW_COLOR[10000]=Gdk::Color.new(16949,19974,25777)
	DOW_COLOR[10000]=Gdk::Color.new(16336,19224,15437)
	######
=end


##IMAGES##
##########
#CONSTANTS FOR FACES
Face_smile="images/faces/face-smile.png"
Face_cool="images/faces/face-cool.png"
Face_win="images/faces/face-win.png"
Face_sad="images/faces/face-sad.png"
Face_worried="images/faces/face-worried.png"

#CONSTANTS FOR SYMBOLS
Flag_flag="images/symbols/flag.png"
Flag_question="images/symbols/flag-question.png"
Mine_mine="images/symbols/mine.png"
Mine_exploded="images/symbols/bang.png"

#CONSTANTS FOR ICONS
Logo="images/icons/logo.png"
Opened="images/icons/opened.png"
Closed="images/icons/closed.png"

#CONSTANTS FOR NUMBERS
NR=[]
NR[1]="images/numbers/one.png"
NR[2]="images/numbers/two.png"
NR[3]="images/numbers/three.png"
NR[4]="images/numbers/four.png"
NR[5]="images/numbers/five.png"
NR[6]="images/numbers/six.png"
NR[7]="images/numbers/seven.png"
NR[8]="images/numbers/eigth.png"



#CONSTANTS FOR FACE STATUS (image[...])
Smile=1
Cool=2
Win=3
Sad=4
Worried=5

#CONSTANTS FOR FIELD ATTRIBUTE
F_nil=0
F_empty=1
F_flag=2
F_flag_question=3
F_mine=4
F_exploded_mine=5
F_hint=6

#CONSTANTS FOR LABELs in settings
Label_text=[]
Label_text[1]="Columns"
Label_text[2]="Lines"
Label_text[3]="Mines"

#CONSTANTS FOR RADIO_BUTTONs in settings
Difficulty_text=[]
Difficulty_text[1]="Easy"
Difficulty_text[2]="Normal"
Difficulty_text[3]="Hard"
Difficulty_text[4]="User"

#CONSTANTS FOR TIME LABEL in settings
Time_text=[]
Time_text[1]="little"
Time_text[2]="normal"
Time_text[3]="much"
Time_text[4]="very much"

#CONSTANTS FOR DIIFICULTY LEVELS
Difficulty_size_x=[]
Difficulty_size_y=[]
Difficulty_mines=[]

Difficulty_size_x[1]=12
Difficulty_size_y[1]=10
Difficulty_mines[1]=12

Difficulty_size_x[2]=20
Difficulty_size_y[2]=12
Difficulty_mines[2]=35

Difficulty_size_x[3]=28
Difficulty_size_y[3]=14
Difficulty_mines[3]=75

#CONSTANTS FOR GAME STATUS
NOTHING=0
CHANGED=1
PLAYING=2
FINISHED=3


#TEXT FOR COLUMNS IN HIGHSCORE-LIST
COLUMN_TEXT=["Ranking","Name","Time","Points"]


#CHEATS ;)
CHEATS=[]
CHEATS[0]=["m","i","n","e","s","|"] #Minen werden angezeigt!
CHEATS[1]=["t","i","m","e","|"] #Mehr Zeit
CHEATS[2]=["h","i","n","t","|"]
CHEATS[3]=["s","e","e","t","h","e","m","i","n","e","s","|"]
CHEATS[4]=["i","a","m","t","h","e","w","i","n","n","e","r","|"]
CHEATS[5]=["+","|"] #hellere Farbe
CHEATS[6]=["-","|"] #dunklere Farbe
#"|" ist nötig, zu zeigen, dass der Cheat zu Ende ist...


CHEAT_TIME=0.5 #Zeit, die für jede gedrückte Taste beim "cheaten" bleibt...




class FAIRwindow < Window

	def initialize
		super
		
		modify_bg(STATE_NORMAL,Stock_color)
		#signal_connect("key-press-event") { |widget,event|
		#	destroy if event.keyval==ESC
		#}
		set_icon(Gdk::Pixbuf.new(Logo))
	end
end


class Highscore

	attr_reader :name, :time, :percent
	
	@name
	@time
	@percent
	
	def initialize(n="---",t="---",p="---")
		@name=n
		@percent=p
		@time=t
	end
end


class Bestenliste

	def initialize(start_page=1)
		@actual_page=start_page
		
		@w=FAIRwindow.new
		@w.set_title("Highscores")
		@w.set_border_width(8)
		@w.set_modal(true)
		
		load_highscores
		
		
		
		@close_button=Button.new(Stock::CLOSE)
		@close_button.signal_connect("clicked") {@w.destroy}
		
		@delete_button=Button.new(Stock::DELETE)
		@delete_button.signal_connect("clicked") {
			if File.exists?("#{SAVE_PATH}highscores.sav")
				File.delete("#{SAVE_PATH}highscores.sav")
				
				load_highscores
				switch_page(@actual_page)
			end
		}
		
		
		@hbox=HBox.new(true,5)
		@hbox.pack_start(@delete_button,false,true)
		@hbox.pack_start(@close_button,false,true)
		
		
		
		@vbox=VBox.new(false,2)
		@vbox.pack_start(new_menu,false,true)
		
		
		@first_time=true
		switch_page(1)
		
		@w.add(@vbox).show_all
	end
	
	def load_highscores
		@h=[]
		4.times { | i | @h[i]=[]}
		
		
		if File.exists?("#{SAVE_PATH}highscores.sav")
			datei=File.new("#{SAVE_PATH}highscores.sav","r")
			@h=Marshal.load(datei)
			datei.close
			
		  4.times { | j |
			for i in @h[j].length...10 do
				@h[j][i]=Highscore.new
			end
		  }
		else
			4.times { | i |
				10.times { | j |
					@h[i][j]=Highscore.new
				}
			}
		end
	end
	
	def new_menu
		#TODO: maybe: OptionMenu.new.....
		item_f=ItemFactory.new(ItemFactory::TYPE_OPTION_MENU,'<main>',nil)
		
		menu_items = [
		  ["/#{Difficulty_text[1]}",ItemFactory::ITEM,nil,nil,Proc.new{switch_page(1)}],
		  ["/#{Difficulty_text[2]}",ItemFactory::ITEM,nil,nil,Proc.new{switch_page(2)}],
		  ["/#{Difficulty_text[3]}",ItemFactory::ITEM,nil,nil,Proc.new{switch_page(3)}],
		  ["/#{Difficulty_text[4]}",ItemFactory::ITEM,nil,nil,Proc.new{switch_page(4)}]
		]
		
		item_f.create_items(menu_items)
		
		
		return item_f.get_widget('<main>')
	end
	
	def switch_page(n)
	  if !@first_time
		@view.destroy
		@vbox.remove(@hbox)
	  end
		@first_time=false
		page=n-1
		
		@actual_page=page
		
		model=ListStore.new(String,String,String,String) #PLATZ,NAME,ZEIT,PROZENT
		@view=TreeView.new(model)
		
		
		item=[]
		@h[page].length.times { | i |
			item[i]=model.append
			item[i][0]="#{i+1}"
			item[i][1]="#{@h[page][i].name}"
			
			
			if @h[page][i].time=="---" then item[i][2]=@h[page][i].time
			else item[i][2]=time_in_string(@h[page][i].time)
			end
			
			if @h[page][i].percent=="---" then item[i][3]=@h[page][i].percent
			else item[i][3]="#{@h[page][i].percent}"
			end
		}
		
		renderer=[]
		column=[]
		4.times { | i |
			renderer[i]=CellRendererText.new
			column[i]=TreeViewColumn.new("#{COLUMN_TEXT[i]}",renderer[i],:text => i)
			@view.append_column(column[i])
		}
		
		@vbox.pack_start(@view,true,true,8)
		@vbox.pack_start(@hbox,false,true)
		@w.show_all
	end
end





class Mine

	@x; @y
	
	attr_reader :x, :y
	attr_writer :x, :y

	def set_x_y(x,y)
		@x=x
		@y=y
	end
end

class Attribute

	@attribute
	
	attr_reader :attribute
	attr_writer :attribute
	
	def initialize
		@attribute=F_nil
	end
end

class Field < Button

	@x; @y
	@usr; @cpu
	@image_anzahl
	@hint
	@opened
	
	attr_reader :x, :y, :usr, :cpu,  :image_anzahl, :hint, :opened
	attr_writer :x, :y, :usr, :cpu,  :image_anzahl, :hint, :opened
	
	def initialize
		super
		
		image_anzahl=nil
		
		@usr=Attribute.new
		@cpu=Attribute.new
		@opened=false
	end
	
	def set_x_y(set_x,set_y)
		@x=set_x
		@y=set_y
	end
end

class Help

	def initialize
		w=FAIRwindow.new
		w.set_title( "Help" )
		w.set_border_width(10)
		
		@notebook=Notebook.new
		create_pages
		
		w.add(@notebook).show_all
		
		@notebook.signal_connect("switch_page") { |widget,page,num_page|
			page_switch(widget,page,num_page)
		}
		@notebook.next_page #nur nötig, damit "Offen"-Bild
		@notebook.prev_page #bereits am Anfang angezeigt wird
	end
	
	def create_pages
		help_text=[]
		help_text[1]="Introduction"
		help_text[2]="The Details"
		help_text[3]="Strategy"
		
	  1.upto(help_text.length - 1) { | i |
		buffer="  #{help_text[i]}"
		
		view=TextView.new
		view.editable=false
		view.cursor_visible=false
		
		if File.exists?("help/(#{i}).txt")
			datei=File.new("help/(#{i}).txt")
			
			view.buffer.text=buffer + "\n\n"
			
			zeile=datei.gets
			while zeile != nil
				if zeile.chomp == "{"
					begin
						line=datei.gets
						if line.chomp != "}" and line[0..0] != "#"
							image=Gdk::Pixbuf.new(datei.gets.chomp)
							
							iter=view.buffer.get_iter_at_line(line.to_i + 2)
							view.buffer.insert(iter,image)
						end
					end until line.chomp == "}"
					
				elsif zeile[0..0] != "#"
					view.buffer.text += zeile
				end
				
				zeile=datei.gets
			end
			datei.close
		else
			view.buffer.text="No help for topic  \"#{help_text[i]}\"  vorhanden"
		end
		sw=ScrolledWindow.new
		sw.set_policy(POLICY_AUTOMATIC,POLICY_AUTOMATIC)
		sw.add(view)
		
		label_box=HBox.new
		icon=Image.new(Closed)
		label_box.pack_start(icon)
		icon.set_padding(3,1)
		label=Label.new(buffer)
		label_box.pack_start(label)
		label_box.show_all
		
		@notebook.append_page_menu(sw,label_box,nil)
	  }
	end
	
	def set_page_pixmaps(notebook,page_num,pic)
		child=notebook.get_nth_page(page_num)
		label=notebook.get_tab_label(child).children[0].set(pic)
	end
	
	def page_switch(notebook,page,page_num)
		old_page_num=notebook.page
		set_page_pixmaps(notebook,page_num,Opened)
		set_page_pixmaps(notebook,old_page_num,Closed)
	end
end






def time_in_string(time) #returns time[sec] in string:  02:12
	time=time.to_f
	time_min=(time / 60)
	time_sec=((time_min - time_min.to_i) * 60).to_i
	time_min=time_min.to_i
	
	show_time=""
	if time_min < 10
		show_time="0"
	end
	show_time+=time_min.to_s+":"
	
	if time_sec < 10
		show_time+="0"
	end
	show_time+=time_sec.to_s
	
	return show_time
end

def gegenzahl(zahl)
	return zahl - zahl * 2
end

def spielzeit(x,y,m,t=1)
	size_x=x.to_f
	size_y=y.to_f
	mines=m.to_f
	time=t.to_f
	
	fields=size_x*size_y
	if fields==0 then	fields+=1 end
	z=(900 * mines) / fields * time
	return z.to_i
end

def check_difficulty(prozent)
	if prozent < 12
		return "Easy"
	elsif prozent < 18
		return "Normal"
	elsif prozent < 25
		return "Hard"
	else
		return "Extreme"
	end
end




def getdate #optional Time.now (in string...)...
	date=Time.now.to_a #sec, min, hour, day, month, year, wday, yday, isdst, zone
	
	date_s=DOW[date[6]] + ", "
	
	date_s += "0" if date[3] < 10
	date_s += date[3].to_s + "."
	
	date_s += "0" if date[4] < 10
	date_s += date[4].to_s + "." + date[5].to_s
	return date_s
end

def runde_auf_kommastellen(z,kommastellen) #"rundet" auf übergebene »kommastellen«
	#(z.B.: runde_auf_kommastellen(2.464323, 2) => 2.46)...
	
	kommast = z- z.to_i #Kommastellen der Zahl (zb: 2.34 => 0.34)
	
	mult=1
	kommastellen.times {mult*=10}
	
	kommast*=mult
	
	mit_kommast=z.to_i + (kommast.round.to_f / mult)
	return mit_kommast
end
