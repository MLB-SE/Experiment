MLB
=====================================================================

This repository presents a new symbolic execution tool based on machine learning and the experiment data collected during the evaluation of the tool. The brand-new tool, which we call MLB, is described in the paper *Symbolic Execution of Complex Code Driven By Machine Learning Based Constraint Solving*. 


Abstract
--------

During program traversing, symbolic execution collects path conditions and feeds them directly to a constraint solver to solve. However, complex path conditions, like non-linear constraints, which widely appear in programs, cannot be efficiently solved by existing solvers.

To overcome the limitations, we adapt the classical symbolic execution framework with a new machine learning optimization technique. Our framework, MLB, encodes not only the simple linear path conditions, but also non-linear arithmetic operations, and even black-box function calls of library methods, into symbolic path conditions. We transform the feasibility problem of the path conditions into an optimization problem.
Then, our framework interacts with the underlying optimization solver to guess and validate potential valuations for the variables in the path conditions, and to guide the solver to converge quickly to the correct answer by machine learning.

We implement MLB by extending Symbolic Path Finder (SPF). To evaluate MLB's performance, we use it to generate test cases for 16 real case programs, with a total number of 290 methods that are full of non-linear operations, float point arithmetic and even native method calls. Experiment results show that MLB generates 100% to 300% higher coverage and is 25% to 400% more efficient than the state-of-the-art tools.

Overview
--------

Directory Structure
--------

Usage
--------



