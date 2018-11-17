package Contrib;

import java.net.URL;
import java.io.*;

/** A class to allow use of the ncsa ISMAP format in java applets. **/
/** version 1.0 writen for Beta2 on 12/27/95 by Dan Cohn, cohnd@rpi.edu **/
/** code for n-sided polygons based on ncsa httpd code by Eric Haines **/
/** usage example: 
      Ismap zones = new Ismap();
      zones.addrect("AreaA",10,10,20,20); //AreaA a rect from (10,10) to (20,20)
      zones.addcirc("AreaB",40,45,5); //AreaB a circle at (40,45) radius 5
      zones.addrect("AreaC",25,30,27,32); //AreaC another rectangle
      zones.addzone("poly AreaD 40,60 45,60 50,65 50,70 35,70")// AreaD a blob
      zones.adddefault("OtherArea");
      zones.lookup(15,15) will return "AreaA" as (15,15) falls in that region.
      zones.lookup(100,100) will return "OtherArea", as an undefined point. **/
/** ISMAP compliant map files may also be loaded by url reference in the 
    constructor or by loadmap() or addmap(). Note that files must not have .map
    extensions to be passed by the server. **/

public final class Ismap {
   /** rather than consulting the server, we will buffer file locally **/
   private String mapdetail[];
   /** default case if all else fails **/
   private String Default;
   /** position to offset from given values when computing area **/
   private int xoffset, yoffset;
   /** keep track of number of lines in our definitions file **/
   private int numlines;
   /** result string when match is found **/
   protected String result;
   /** number of lines available per map file. reduce for memory conservancy **/
   protected int MAXLINES=512;
   protected int MAXVERTS=128;

   /** generic constructor allocates mapdetail and sets offsets to 0 **/
   public Ismap() {
      this.mapdetail = new String[MAXLINES];
      this.xoffset = this.yoffset = 0;
      this.numlines = 0;
   }

   /** constructs a new Ismap which starts off by loading a url as map data **/
   public Ismap(String mapurl) {
      this.mapdetail = new String[MAXLINES];
      this.xoffset = this.yoffset = 0;
      this.loadmap(mapurl);
   }
      
   /** and one more constructor to start off with a url map and an x,y offset */
   public Ismap(String mapurl, int xoffset, int yoffset) {
      this.mapdetail = new String[MAXLINES];
      this.xoffset=xoffset;
      this.yoffset=yoffset;
      this.loadmap(mapurl);
   }

   public void clearmap() {
      this.numlines = 0;
   }
      
   public boolean loadmap(String mapurl) {
      this.numlines=0;
      return(this.addmap(mapurl));
   }

   public boolean addmap(String mapurl) {
      int linepos = 1;
      int streambyte;
      char localln[]=new char[80];
      try {
         URL remotemap = new URL(mapurl);
         InputStream mapis = remotemap.openStream();
         do {
            streambyte=mapis.read();
            localln[linepos-1]=(char)streambyte;
            if (localln[linepos-1]=='\n') {
               this.mapdetail[numlines]=String.copyValueOf(localln,0,linepos-1);
               numlines++;
               linepos=0;
            }
            linepos++;
         } while (streambyte!=-1);
      }
      catch(java.net.MalformedURLException e) {
         System.out.println("Malformed URL! exception");
         return(false);
      }
      catch(java.io.IOException e) {
         System.out.println("Error reading from URL! exception");
         return(false);
      }
      return true;
   }

   public void offset(int x, int y) {
      this.xoffset=x;
      this.yoffset=y;
   }

   private boolean checkline(int testx, int testy, String thisstr) {
      int i1,i2,coordnum;
      String type, name, scoordpair;
      int coordpair[][] = new int[MAXVERTS][2];
      thisstr.trim(); /* cut any excess whitspace */
      if ((!thisstr.startsWith("#"))&&(thisstr.length()>1)) {
         i1=thisstr.indexOf(" ");
         type=thisstr.substring(0,i1);
         if (!type.equals("default")) 
            i2=thisstr.indexOf(' ',i1+1);
         else { /* default case */
            this.Default=thisstr.substring(i1+1);
            return false;
         }
         name=thisstr.substring(i1+1,i2);
         i1=i2;
         i2=thisstr.indexOf(' ',i1+1);
         for (coordnum=0;i2!=-1;coordnum++) {
            scoordpair=thisstr.substring(i1+1,i2);
            coordpair[coordnum][0]=
               Integer.valueOf(scoordpair.substring
                              (0, scoordpair.indexOf(","))).intValue();
            coordpair[coordnum][1]=
               Integer.valueOf(scoordpair.substring
                              (scoordpair.indexOf(",")+1)).intValue();
            i1=i2;
            i2=thisstr.indexOf(' ',i1+1);
            if ((i2==-1)&&(i1!=thisstr.length())) i2=thisstr.length();
         }
         coordpair[coordnum][0]=-1; // end of pairs sentinel for polygon code
         if (type.equals("rect")) 
            if (pointinrect(testx,testy,coordpair)) {
               this.result=name;
               return true;
            }
         if (type.equals("circ"))
            if (pointincirc(testx,testy,coordpair)) {
               this.result=name;
               return true;
            }
         if (type.equals("poly"))
            if (pointinpoly(testx,testy,coordpair)) {
               this.result=name;
               return true;
            }
      }
      return false; // not within boundaries of this line
   }

   public String lookup (int xcoord, int ycoord) {
      int linecount=0;
      xcoord+=this.xoffset;
      ycoord+=this.yoffset;
      for (linecount=0;linecount<numlines;linecount++) {
         if (checkline(xcoord,ycoord,String.valueOf(this.mapdetail[linecount])))
            return (this.result);
      }
      if (this.Default.length()>0)
         return (this.Default);
      System.out.println ("The point ("+xcoord+","+ycoord+") has not been "+
                          "accounted for and no default was defined.");
      return("");
   }

   public void dumpmap() {
      int linecount=0;
      for (linecount=0;linecount<numlines;linecount++) {
         System.out.println(String.valueOf(mapdetail[linecount]));
      }
   }

   public void addzone(String mapinfo) {
      this.mapdetail[numlines++]=mapinfo;
   }

   public void adddefault(String regionname) {
      this.mapdetail[numlines++]="default "+regionname;
   }

   public void addrect(String regionname, int x1, int y1, int x2, int y2) {
      this.mapdetail[numlines++]="rect "+regionname+" "+x1+","+y1+" "+x2+","+y2;
   }

   public void addcirc(String regionname, int x1, int y1, int r) {
      int outerpt=x1+r;
      this.mapdetail[numlines++]="circ "+regionname+" "+x1+","+
                                  y1+" "+outerpt+","+y1;
   }

   private boolean pointinrect(int x, int y, int coords[][]){
      return ((x>=coords[0][0]&&x<=coords[1][1])&&
              (y>=coords[0][1]&&y<=coords[1][1]));
   }

   private boolean pointincirc(int x, int y, int coords[][]) {
      int radius1, radius2;
      radius1=((coords[0][1]-coords[1][1])*(coords[0][1]-coords[1][1])) +
              ((coords[0][0]-coords[1][0])*(coords[0][0]-coords[1][0]));
      radius2=((coords[0][1]-y)*(coords[0][1]-y)) +
              ((coords[0][0]-x)*(coords[0][0]-x));
      return (radius2<=radius1);
   }

   private boolean pointinpoly(int tx, int ty, int pgon[][]) {
      int i, numverts, inside_flag, crossings;
      boolean xflag0;
      double  stop, y;

      for (i=0; pgon[i][0]!=-1 && i < MAXVERTS; i++);
      numverts=i;
      crossings=0;

      y=pgon[numverts-1][1];

      if ((y>=ty)!=(pgon[0][1]>=ty)) {
         if ((xflag0=(pgon[numverts-1][0]>=tx))==
         (pgon[0][0]>=tx)) {
            if (xflag0)
               crossings++;
         }
         else {
            if ((pgon[numverts-1][0]-(y-ty)*
            (pgon[0][0]-pgon[numverts-1][0])/
            (pgon[0][1]-y)) >= tx)
               crossings++;
         }
      }

      stop=numverts;

      for (int index=1; index<stop; y=pgon[index][1],index++) {
         if (y >= ty ) {
            while ((index<stop)&&(pgon[index][1]>=ty))
               index++;
            if (index>=stop)
               break;
            if ((xflag0=(pgon[index-1][0]>=tx))==(pgon[index][0]>=tx)) {
               if (xflag0)
                  crossings++;
            }
            else {
               if((pgon[index-1][0]-(pgon[index-1][1]-ty)*
               (pgon[index][0]-pgon[index-1][0])/
               (pgon[index][1]-pgon[index-1][1]))>=tx)
                  crossings++;
            }
         }
         else {
            while ((index<stop)&&(pgon[index][1]<ty))
               index++;
            if (index>=stop)
               break;
            if ((xflag0 = (pgon[index-1][0]>=tx))==(pgon[index][0]>=tx)) {
               if (xflag0)
                  crossings++;
            }
            else {
               if ((pgon[index-1][0]-(pgon[index-1][1]-ty)*
               (pgon[index][0]-pgon[index-1][0])/
               (pgon[index][1]-pgon[index-1][1]))>=tx)
                  crossings++;
            }
         }
      }
      inside_flag = crossings & 0x01;
      return (inside_flag>0);
   }
}
