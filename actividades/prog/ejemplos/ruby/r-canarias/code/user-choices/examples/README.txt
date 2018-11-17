		      == Command-line arguments ==

+ Help is generated for you:

prompt> ruby command-line.rb --help

+ Options are placed into a hash, as is the argument list

prompt> ruby command-line.rb --choice cho sophie paul dawn me

+ The usual variant ways of specifying options

prompt> ruby command-line.rb -c choice
prompt> ruby command-line.rb -cchoice
prompt> ruby command-line.rb --choi choice
prompt> ruby command-line.rb --choi choice -- -name1- -name2-

+ There are error messages for extra and malformed options

prompt> ruby command-line.rb --choice
prompt> ruby command-line.rb --other 3 -- choice

			 == Default values ==

+ You can specify default values when you define choices.

prompt> ruby default-values.rb --choice specific
prompt> ruby default-values.rb

+ Note: defaulting behavior applies to other sources of choices, not
   just command lines.

		       == Optional arguments ==

+ When single optional arguments are required, the result is not a
  list - it's either the value or nothing. 

prompt> ruby default-values.rb only-arg

== Type checks and conversions

+ There are ways to describe what kind of thing an argument
   is. (Currently limited.)

prompt> ruby types.rb --a-or-b not-a  argument
prompt> ruby types.rb --must-be-integer 1d argument
prompt> ruby types.rb --option-list first,second argument

+ Integer-valued options are converted into integers, not left as
   strings:

prompt> ruby types.rb --must-be-integer 3 argument

+ Type-checking and type conversions apply to all sources, not just
   command lines. 

		       == Required Arguments ==

+ You can require exactly one argument:

prompt> ruby types.rb
prompt> ruby types.rb argument extra-argument

	      == Switches (values without arguments) ==

+ Switches can also be specified.  Typically, their values are either
   true or false (rather than strings or numbers). 

+ How they are given on the command line may be unfamiliar to you:

prompt> ruby switches.rb --help

+ Examples of use:

prompt> ruby switches.rb 1 2
prompt> ruby switches.rb --switch 1 2
prompt> ruby switches.rb -s 2 1 
prompt> ruby switches.rb --no-switch 1 2

== A range of arguments == 

+ You can require that the number of arguments be in a range. In this
   case, it's 2-4:

prompt> ruby switches.rb 1 
prompt> ruby switches.rb 1  2 
prompt> ruby switches.rb 1  2  3 4
prompt> ruby switches.rb 1  2  3 4 5

+ You can also require any particular exact number of arguments:

prompt> ruby two-args.rb 1
prompt> ruby two-args.rb 1 2 
prompt> ruby two-args.rb 1 2 3 

			 == Postprocessing ==

+ You can also postprocess choices into a form more convenient for the
  rest of the program.

prompt> ruby postprocess.rb 1 2

			== Multiple Sources ==

+ First see what happens when no arguments are given.

prompt> ruby command-line.rb

+ Create a file named ms-config.xml in your home / login folder, and
  put the following in it. (Note the underscore in "ordinary_choice" -
  this is the name of the choice, not the representation of that choice
  on the command line.)

       <config>
         <ordinary_choice>greetings</ordinary_choice>
       </config>

prompt> ruby multiple-sources.rb 

Set an environment variable ms_ordinary_choice with value 'hi'. 

prompt> ruby multiple-sources.rb

Which takes precedence, the environment or the configuration file?
(This is configurable.) 

The command line arguments have highest precedence of all:

prompt> ruby multiple-sources.rb --ordinary-choice hello

