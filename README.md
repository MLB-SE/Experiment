# Experiment
This repository presents a new symbolic execution tool which is based on machine learning and the experiment data collected during the evaluation of the tool. The brand-new tool, which we call SPF-MLB, is described in the paper Symbolic Execution of Complex Code Driven By Machine Learning Based Constraint Solving.

Abstact
----------

Symbolic execution is a program analysis technique which has been widely used in many open-source testing tools and industrial practises. But existing symbolic execution tools are not able to support real world programs where existing solvers have limitations on dealing with complicated constraints. To overcome this limitation, we present a new symbolic execution tool which is based on machine learning. We call this tool SPF-MLB. SPF-MLB extracts and encodes the complicated constraints from real world programs with an extension of Symbolic PathFinder and solves the complicated constraints by sampling from a model which is implemented by a classi?cation model discriminating good solutions from bad solutions. On this basis, we extend SPF-MLB by providing black-box and white-box execution modes to support programs contain the third library method calls. When SPF-MLB fails to ?nd a solution for a path condition, it reports the false negative rate for users to reference. To evaluate SPF-MLB¡¯s performance, we use SPF-MLB to generate test cases for 16 programs and compare the coverage and analysis time with 4 state-of-art tools including jCUTE, SPF-Mixed, SPF-CORAL and Concolic Walk. Experiment results showed that our tool is two- to three-times more e?ective and 2 times more e?cient than the 4 tools. 
 

