/*
 * Default FlowPlayer fullscreen opener.
 * http://flowplayer.sourceforge.net
 */

function flowPlayerOpenFullScreen(config) {
  var winWidth = window.screen.availWidth;
  var winHeight = window.screen.availHeight;
  var fullScreenWindow = window.open('http://flowplayer.org/video/1_20/fullscreen.html?config='+config, 'FlowPlayer', 'left=0,top=0,width='+winWidth+',height='+winHeight+',status=no,resizable=yes');
}

function flowPlayerExitFullScreen(config) {
  self.close();
}

