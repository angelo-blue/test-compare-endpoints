#!/bin/bash
if [[ $# -ne 2 ]] ; then
    echo 'two parameters required: inputFile outputDir.  Exiting.'
    exit 1
fi

inFile=$1
outDir=$2
echo Reading input from "$inFile"
echo Writing output to "$outDir"
echo ===========================
filelines=`cat $inFile`
echo Validating...
counter=1
for line in $filelines ; do
    size=${#line}
    if [[ $size -ne 9 ]] ; then
        echo "Validation failed - size of $size found at line $counter"
        exit 1
    fi
    let counter++
done
 
echo Starting compare...
for line in $filelines ; do
  ./compare_things.sh $outDir  $line
done
