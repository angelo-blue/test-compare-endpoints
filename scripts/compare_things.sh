#!/bin/bash
# arg1 - directory name to save results
# arg2 - id to pass through to the two endpoints
# writes a result line to results.txt
# if different, writes diff to results_(arg1).txt

if [[ $# -ne 2 ]] ; then
    echo 'two parameters required: directory,id.  Exiting.'
    exit 1
fi

resultsDir=$1
id=$2

if [ ! -d "$resultsDir" ]; then
    echo "directory $resultsDir not found"
    exit 1
fi

# call 1 with id and store result in res1
res1=$(./do_thing1.sh $id)

# call 2 with id and store result in res2
res2=$(./do_thing2.sh $id)

# diff into var df.  note the <() is process substitution
df=$(diff <(echo $res1) <(echo $res2))  

if [ "$df" ]; then
  echo $id different >> "$resultsDir"/results.txt
  echo $df >> "$resultsDir"/results_$id.txt
  echo res1=$res1 >> "$resultsDir"/results_$id.txt
  echo res2=$res2 >> "$resultsDir"/results_$id.txt
else
  echo $id same >> "$resultsDir"/results.txt
fi
