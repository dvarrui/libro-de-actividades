#!/usr/bin/perl
#
# This is a script to turn C and C++ source code into pretty HTML

# These are the colors for the keywords
$color{'comment'} = 'red';
$color{'cppkeyword'} = 'blue';
$color{'language'} = 'blue';
$color{'datatype'} = 'green';
$color{'string'} = 'black';

# These are the known language keywords
$language{'if'} = 1;
$language{'else'} = 1;
$language{'while'} = 1;
$language{'do'} = 1;
$language{'switch'} = 1;
$language{'case'} = 1;
$language{'default'} = 1;
$language{'const'} = 1;
$language{'static'} = 1;
$language{'struct'} = 1;
$language{'enum'} = 1;
$language{'union'} = 1;
$language{'typedef'} = 1;
$language{'return'} = 1;

# These are the known datatype keywords
$datatype{'unsigned'} = 1;
$datatype{'char'} = 1;
$datatype{'short'} = 1;
$datatype{'int'} = 1;
$datatype{'long'} = 1;
$datatype{'void'} = 1;

# Custom datatype keywords
$datatype{'Uint8'} = 1;
$datatype{'Sint8'} = 1;
$datatype{'Uint16'} = 1;
$datatype{'Sint16'} = 1;
$datatype{'Uint32'} = 1;
$datatype{'Sint32'} = 1;

# Let's do it!!
print '<BODY bgcolor="#FFEBCD" TEXT="#000000">', "\n";
print "<PRE>\n";
while (<STDIN>) {

	# Catch '<' and '>' and '&'
	$_ =~ s,&,&amp;,g;
	$_ =~ s,<,&lt;,g;
	$_ =~ s,>,&gt;,g;

	# Catch strings
	$_ =~ s,"([^"]*)","<FONT color="$color{string}">\1</FONT>",g;

	# Catch comments
	if ( $_ =~ /\/\*/ ) {
		$_ =~ s,/\*,<FONT color="$color{comment}">/\*,g;
		$in_comment = 1;
	}
	if ( $_ =~ /\*\// ) {
		$_ =~ s,\*/,\*/</FONT>,g;
		$in_comment = 0;
	}
	if ( $in_comment == 1 ) {
		print;
		next;
	}
	if ( $_ =~ /\/\// ) {
		$_ =~ s,//,<FONT color="$color{comment}">//,;
		$_ =~ s,$,</FONT>,;
	}

	# Catch C preprocessor statements
	$_ =~ s,^(\s*)(#[^\s]+)(\s),\1<FONT color="$color{cppkeyword}">\2</FONT>\3,;

	# Catch language keywords
	foreach $keyword (keys %language) {
		$_ =~ s,(\s*)($keyword)([\s\(]),\1<FONT color="$color{language}">\2</FONT>\3,;
	}

	# Catch datatype keywords
	foreach $keyword (keys %datatype) {
		$_ =~ s,(\s*)($keyword\s*\*),\1<FONT color="$color{datatype}">\2</FONT>,g;
		$_ =~ s,(\s*)($keyword)(\s+[^\s\*]),\1<FONT color="$color{datatype}">\2</FONT>\3,g;
	}
	print;
}
print "</PRE>\n";
