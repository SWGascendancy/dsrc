# get the list of files to process from renames.rsp
open(RSP, "pie.rsp");

$changedCount = 0;
$unchangedCount = 0;
$substitutions = 0;

# process all the files
while (<RSP>)
{
	# get rid of the freakin' newline
	chomp;

	# provide status
	print "processing " . $_;

	# open the files for i/o
	$file = $_;
	$new = $file . ".new";
	open(IN, $file);
	open(OUT, ">" . $new);

	# process all the lines in the input file
	$changed = 0;
	while (<IN>)
	{
		# keep around the original line so we can check if it has been changed
		$save = $_;
		
		# apply the transformations
		s/\.tpf/.iff/g;

		# update the changed flag
		if ($save ne $_)
		{ 
			$changed = 1;
			$substitutions += 1;
		}
		
		# emit the [possibly] changed line
		print OUT;
	}
	
	# done accessing the contents of the files
	close(IN);
	close(OUT);

	if ($changed == 1)
	{
		$changedCount += 1;

		# check the file out and replace its contents
		system("p4 edit " . $file);
		unlink($file);
		rename($new, $file);

		# provide results
		print " [changed]\n";
	
	}
	else
	{
		$unchangedCount += 1;

		# remove temporary work
		unlink($new);

		# provide results
		print " [unchanged]\n";
	}
}

close(RSP);

print $changedCount . " changed files, " . $unchangedCount . " unchanged files, " . $substitutions . " substitutions\n";
