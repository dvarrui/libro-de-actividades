require 'sdl'

SDL.init( SDL::INIT_VIDEO )
screen = SDL::setVideoMode(640,480,16,SDL::SWSURFACE)

font = SDL::BMFont.open("juegos/iconos/font.bmp",SDL::BMFont::TRANSPARENT)

y = 0

while true
  while event = SDL::Event2.poll
    case event
    when SDL::Event2::KeyDown, SDL::Event2::Quit
      exit
    end
  end
  screen.fillRect(0,0,640,480,0)

  y = (y + 1) % 480
  #font.textout(screen,"BitMapFont Tesging..",40,y)
  font.textout(screen,"David Vargas Ruiz...",40,y)
  

  screen.updateRect(0,0,0,0)
  sleep 0.005
end
