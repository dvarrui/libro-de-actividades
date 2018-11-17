require 'sdl'

SDL.init( SDL::INIT_VIDEO )
screen = SDL::setVideoMode(640,480,16,SDL::SWSURFACE)
SDL::WM::setCaption('collision.rb','collision.rb icon')
$image = SDL::Surface.loadBMP("juegos/iconos/icon.bmp")
$image.setColorKey( SDL::SRCCOLORKEY ,0)
$image = $image.displayFormat
$cMap = $image.makeCollisionMap

class Sprite

  attr_reader :x, :y

  def initialize(screen)
    @screen = screen
    @x=rand(screen.w)
    @y=rand(screen.h)
    @dx=rand(11)-5
    @dy=rand(11)-5
  end
  
  def move
    @x, @dx = moveCoord(@x, @dx, xMax)
    @y, @dy = moveCoord(@y, @dy, yMax)
  end

  def bounce
    @dx *= -1
    @dy *= -1
    move
  end

  def draw
    SDL.blitSurface($image,0,0,$image.w,$image.h,@screen,@x,@y)
  end
  
  def collidingWith(sprite)
    $cMap.collisionCheck(@x, @y, $cMap, sprite.x, sprite.y) != nil
  end

  private

  def moveCoord(coord, delta, max)
    coord += delta
    if coord >= max then
      delta *= -1
      coord = max - 1
    end
    if coord < 0 then
      delta *= -1
      coord = 0
    end
    [coord, delta]
  end
  
  def xMax
    @screen.w - $image.w
  end

  def yMax
    @screen.h - $image.h
  end
  
end

def detectCollisions(sprites)
  collisions = []
  for i in (0 ... sprites.size - 1) do
    for j in (i + 1 ... sprites.size) do
      if sprites[i].collidingWith(sprites[j])
        collisions << sprites[i]
        collisions << sprites[j]
      end
    end
  end
  collisions.uniq
end

sprites = (1..18).collect {Sprite.new(screen)}


while true
  while event = SDL::Event2.poll
    case event
    when SDL::Event2::KeyDown, SDL::Event2::Quit
      exit
    end
  end

  screen.fillRect(0,0,640,480,screen.mapRGB(64, 64, 64))
  sprites.each {|i| i.move}
  detectCollisions(sprites).each {|i| i.bounce}
  sprites.each {|i| i.draw}
  screen.updateRect(0,0,0,0)
end

