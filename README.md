# Weeks 8-9 Lab
In this weeks lab we will work on the computer science concepts of Linked Lists and Sorting. 
We will be doing this by introducing a common task in computational biology: RNA to Amino Acid translation. 
Proteins are encoded in a 20 letter amino acid alphabet but when those proteins are encoded on the genome they are in a 4 letter alphabet. 
To convert from RNA into Amino Acids your cells use a specific code to convert 3 RNA characters (called a codon) into a specific amino acid. 
![](https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/RNA-codons-aminoacids.svg/1024px-RNA-codons-aminoacids.svg.png)

The codon to Amino Acid map is shown in the figure below. 
Notice that each codon encodes only 1 Amino Acid, but each Amino Acid may corespond to multiple codon sequences. 

![](codon.jpg)

Some codons enode a "stop", which halts translation at that point. 
As cells evolve the RNA sequences change which may or may not change the protein sequences. 
Our task will be to translate two (or more) RNA sequences and compare the Amino Acid frequencies. 

## Your task
Create a new linked list type, `AminoAcidLL`, which holds:
* `private char aminoAcid` -- the character representing the Amino Acid stored in this element
* `private String[] codons` -- the codons that represent this Amino Acid
* `private int[] counts` -- the count of the codon usage (`codons.length` will be equal to `counts.length`)

The methods you will need to create and manage your linked lists for each sequence are:
* `private void addCodon(String inCodon)` -- this will (recursively) add the codon to the linked list, if the Amino Acid already exists it will add it to the count on that node, otherwise it will create a new node. 
* `private void totalCount()` -- this will return the number of times this amino acid is used in the sequence (the sum of all of the codon counts).
* `public int aminoAcidCompare(AminoAcidLL inList)` -- this is a recursive method that returns the difference in counts between two lists of Amino Acids (using the `totalCount()` value). 
* `public int codonCompare(AminoAcidLL inList)` -- this is a recursive method that returns the difference in counts between the two lists of Amino Acids based on the individual codon counts (while the `totalCount()` difference may be 0, the codon difference may be non-zero). 
* `public char[] aminoAcidList()` -- returns an array of the amino acids characters (in the order which they are in within the linked list). 
* `public int[] aminoAcidCounts()` -- returns an array of the counts of the amino acids (in the order which they are in within the linked list). 
* `private boolean isSorted()` -- a recursive method that determines if the remainder of a given linked list is already sorted. 

Your class will also have several static methods that are used to construct and sort a linked list
* `public static AminoAcidLL createFromRNASequence(String inSequence)` -- this will take in the RNA sequence and return the linked list containing only the Amino Acids present in the seuqnece, with the codon counts propigated. Note that the STOP codon does not actually exist in the list, but indicates when translation should stop (this may come before the end of the string). 
* `private void sort(AminoAcidLL inList)` -- sorts the given linked list by the Amino Acid character in alphanumeric order. 

## Given 
You will be provided with a java class `AminoAcidResources.java` (it is already in your repository) which will have the following methods:
* `public static char getAminoAcidFromCodon(String s)` -- given a String with 3 characters, returns the Amino Acid character
* `public static String[] getCodonListForAminoAcid(char a)` -- given an Amino Acid character, returns an array of all possible codons as strings. 

## What to turn in
1. `AminoAcidLL.java`
1. `AminoAcidLLTester.java` -- contains at least 10 test cases testing the methods above. 


