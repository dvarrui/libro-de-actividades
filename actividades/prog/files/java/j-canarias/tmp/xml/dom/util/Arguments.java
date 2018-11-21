/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999, 2000  The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */



package david.canarias.xml.dom.util;
import java.lang.Integer;



/**
 * Utility like Unix getopt.
 * 
 * Usage:
 * 
 * int c;
 * 
 *         parseArgumentTokens(argv);
 *         while ( (c =  getArguments()) != -1 ){
 * 
 *          switch (c) {
 *             case 'v':
 *                 System.out.println( "v" );
 *                 break;
 *             case 'V':
 *                 System.out.println( "V" );
 *                 break;
 * 
 * @version $id$
 * @author Jeffrey Rodriguez
 */

public class Arguments {
    private  boolean      fDbug                        = false;
    private  Queue        queueOfSwitches              = new Queue(20);
    private  Queue        queueStringParameters        = new Queue(20);
    private  Queue        queueOfOtherStringParameters = new Queue(20);
    private  String[]     messageArray                 = null; 
    //private  int          lastPopArgument              = 0;


    public Arguments() {
    }

    /**
     * Takes the array of standar Args passed
     * from main method and parses the '-' and
     * the characters after arg.
     * 
     * - The value -1 is a special flag that is
     * used to indicate the beginning of the queue
     * of flags and it is also to tell the end of
     * a group of switches.
     * 
     * This method will generate 3 internal queues.
     * - A queue that has the switch flag arguments.
     *   e.g. 
     *          -dvV
     *   will hold  d, v, V, -1.
     * 
     * - A queue holding the string arguments needed by
     *   the switch flag arguments.
     *   If character -p requires a string argument.
     *   The string argument is saved in the string argument
     *   queue.
     * 
     * - A queue holding a list of files string parameters
     *   not associated with a switch flag.
     *   -a -v -p myvalue  test.xml test1.xml
     *   this queue will containt test.xml test1.xml
     * 
     * @param arguments
     * @param argsWithOptions
     */
    public void    parseArgumentTokens(  String[] arguments , char[] argsWithOptions ){
        //int  theDash         = 0;
        int  lengthOfToken   = 0;
        char []bufferOfToken = null;
        //Object[] temp;

        int  argLength = arguments.length;

        outer:
        for ( int i = 0; i<argLength; i++ ){
            bufferOfToken = arguments[i].toCharArray();
            lengthOfToken = bufferOfToken.length;
            if ( bufferOfToken[0] == '-' ){
                int   token;
                //System.out.println( "argv = " + arguments[i] );
                //System.out.println( "leng = " + lengthOfToken );
                for ( int j = 1; j<lengthOfToken; j++ ){
                    token = bufferOfToken[j];
                    //System.out.println( "token = " + token );
                    queueOfSwitches.push( (Object ) new Integer( token ));
                    for ( int k = 0; k< argsWithOptions.length; k++) {
                        if ( token == argsWithOptions[k] ){
                            if ( this.fDbug  ) {
                                System.out.println( "token = " + token );
                            }
                            //queueOfSwitches.push( (Object ) new Integer( -1 ));
                            queueStringParameters.push( arguments[++i] );
                            continue outer;
                        }
                    }

                }

                if ( i+1 < argLength ){
                    if ( !( arguments[i+1].charAt(0) == '-') ) //next argument not start '-'
                        queueOfSwitches.push( (Object ) new Integer( -1 )); //put -1 marker 
                }

            } else{
                queueOfOtherStringParameters.push( arguments[i] );
            }
        }


        if ( this.fDbug ) {
            queueOfSwitches.print();
            queueStringParameters.print();
            queueOfOtherStringParameters.print();
        }
    }


    /**
     * 
     * @return 
     */
    public  int getArguments(){
        if ( this.fDbug ){
            queueOfSwitches.print();
        }

        //int value = ((Integer ) queueOfSwitches.pop()).intValue();
        //if ( this.fDbug ) {
          //  System.out.println("value = " + value );
        //}
        return queueOfSwitches.empty() ? -1:((Integer ) queueOfSwitches.pop()).intValue();
    }



    /**
     * 
     * @return 
     */
    public String getStringParameter(){
        String    s = (String) queueStringParameters.pop();
        if ( this.fDbug ){
            queueStringParameters.print();
        }
        if ( this.fDbug )  {
            System.out.println( "string par = " + s );
        }
        return s;
    }


    public String getlistFiles(){

        if ( this.fDbug ) {
            queueOfOtherStringParameters.print();
        }

        String s = (String) queueOfOtherStringParameters.pop(); 
        return s;
    }



    public int stringParameterLeft( ){
        return queueStringParameters.size();
    }


    public void setUsage( String[] message ){ 
        messageArray = message;
    }

    public void printUsage() {
        for ( int i = 0; i< messageArray.length; i++ ){
            System.err.println( messageArray[i] );
        }
    }

    // Private methods

    // Private inner classes


    private static final int  maxIncrement = 10;

    private  class Queue   {
        //private LinkedList queue;
        private Object[]          queue;
        private int               max;
        private int               front; 
        private int               rear;
        private int               items;


        public Queue( int size) {
            queue  = new Object[size];
            front  = 0;
            rear   = -1;
            items  = 0;
            max    = size;
            //queue = new LinkedList();
        }
        public void push( Object token ) {
            try {
                queue[++rear] = token;
                items++;
            } catch ( ArrayIndexOutOfBoundsException ex ){
                Object[] holdQueue = new Object[max + maxIncrement]; 
                System.arraycopy(queue, 0, holdQueue,0,max );
                queue = holdQueue;
                max   += maxIncrement;
                queue[rear] = token;
                items++;
            }

            //queue.addLast( token );
        }
        public Object pop() {
            Object token = null;
            if ( items != 0 ) {
                token = queue[front++];
                items--;
            }
            return token; 
        }
        public boolean empty(){
            return(items==0);
        }

        public int size(){
            return items;
        }

        public void clear(){
            front  = 0;
            rear   = -1;
            items  = 0;
        }


        public void print(){
            for ( int i = front; i <= rear;i++ ){ 
                System.out.println( "token[ " +  i
                                    + "] = " +  queue[i] ) ;
            }

        }

    }
}
