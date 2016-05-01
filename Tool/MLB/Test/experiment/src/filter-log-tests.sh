#!/bin/bash

# Extract the unit test log from JUNIT's output

if [[ $# != 1 ]]; then
    echo "Usage: ${0} JUNIT_OUTPUT_FILE"
    echo "JUNIT_OUTPUT_FILE should be of form '*.log'."
    exit 1
fi

JUNIT_OUTPUT_FILE=${1}

if [ ! -f ${JUNIT_OUTPUT_FILE} ]; then
    echo "File not found: ${JUNIT_OUTPUT_FILE}"
    exit 2
fi

# In JUNIT's output ,look for those with '#'
if grep -q '#start#' ${JUNIT_OUTPUT_FILE}; then
    testClass=$(echo ${JUNIT_OUTPUT_FILE} | sed -e 's/^\.\?\/\?//' -e 's/\//./g' -e 's/\.log$//')
    testFile=$(basename ${JUNIT_OUTPUT_FILE} .log)
    cat >${testFile} <<-EOF
	import org.junit.Test;

	public class ${testClass} {
EOF
    sed -n '/^#/p' \
	${JUNIT_OUTPUT_FILE} \
    >> ${testFile}
    cat >> ${testFile} <<-EOF
	}
EOF
else
    echo "${JUNIT_OUTPUT_FILE} does not seem to contain JPF generated method summaries.  Aborting."
    exit 3
fi
