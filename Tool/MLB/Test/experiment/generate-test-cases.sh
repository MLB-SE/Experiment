#!/bin/bash

NUMBER_OF_RUNS=1
experiment=${1:-$(date +"%y%m%d-%H%M")}

function disableNonCompilingTests() {
    # From ant's javac output, extract the "file:line" part of error
    # messages (grep and first sed call), and then disable the line in
    # the file (while and second sed call).
    grep '\[javac\].* error: ' \
	| sed -e 's/^\s*\[javac\] //' -e 's/: error: .*$/:/' \
	| sort --unique \
	| while IFS=":" read -r file line; do sed "${line} s/^/\/\//" -i.orig ${file}; done
}


echo ">>> Running experiment \"${experiment}\" ($NUMBER_OF_RUNS iterations)"
for runNumber in $(seq ${NUMBER_OF_RUNS}); do
    ant clean-all
    if ant generate-tests-jpf-MLB ; then #&& fixRaytraceFloatZeros; then
	until ant compile-tests-jpf-MLB; do
	    ant compile-tests-jpf-MLB | disableNonCompilingTests
	done
	if ant run-tests-jpf-MLB report; then
	    mv build.log coverage/
	    mv gen/ MLBgen-${experiment}-${runNumber}/
	    mv coverage/ MLBcoverage-${experiment}-${runNumber}/
	else
	    echo ">>> ERROR: Collecting the coverage failed.  Aborting."
	    exit 2
	fi
    else
	echo ">>> ERROR: Generating the tests failed.  Aborting."
	exit 1
    fi
done

echo ">>> Creating experiment archive gen_coverage_${experiment}.tar.gz"
tar czf MLBgen_coverage_${experiment}.tar.gz MLBgen-${experiment}-*/ MLBcoverage-${experiment}-*/
