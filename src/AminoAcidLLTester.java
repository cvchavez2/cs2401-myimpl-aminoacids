import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AminoAcidLLTester {

    @Test
    public void testFromRNASequence(){
        String sequence = "GGGGCGGCGUGCGACGACUUU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
        char[]expected = {'G','A','C','D','F'};
        char[]result = new char[5];
        int i = 0;
        while(list!=null){
            result[i++] = list.aminoAcid;
            list = list.next;
        }
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAminoAcidList(){
        String sequence = "GGGGCGGCGUGCGACGACUUU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
        char[]expected = {'G','A','C','D','F'};
        char[]result = list.aminoAcidList();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAminoAcidCount(){
        String sequence = "GGGGCGGCGUGCGACGACUUU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
        int[]expected = {1, 2, 1, 2, 1};
        int[]result = list.aminoAcidCounts();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSort(){
        String sequence = "GGGGCGGCGUGCGACGACUUU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
        list = AminoAcidLL.sort(list);
        char[]expected = {'A', 'C', 'D', 'F', 'G'};
        char[]result = list.aminoAcidList();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAminoAcidCompare(){
        String sequence = "GCGGCGUGCGACGACUUUGGG";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
//        char[]result = list.aminoAcidList();
//        int[]codonCount = list.aminoAcidCounts();
//        System.out.println(Arrays.toString(result));
//        System.out.println(Arrays.toString(codonCount));

        String compareSequence = "GCGUGCUGCUUUAAG";
        AminoAcidLL compareList = AminoAcidLL.createFromRNASequence(compareSequence);

        int expected = 6;
        int difference = list.aminoAcidCompare(compareList);
        assertEquals(expected, difference);
//        char [] aminosCompare = compareList.aminoAcidList();
//        int [] aminoCount = compareList.aminoAcidCounts();
//        System.out.println(Arrays.toString(aminosCompare));
//        System.out.println(Arrays.toString(aminoCount));

//        assertArrayEquals(expected, result);
    }

    @Test
    public void testCodonCompare(){
        String sequence = "GCGGCGUGCGACGACUUUGGG";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
//        while(list!=null){
//            System.out.println("Amino: " + list.aminoAcid);
//            for(int i = 0; i<list.codons.length; i++){
//                System.out.println(list.codons[i] + " " + list.counts[i]);
//            }
//            list = list.next;
//        }

        System.out.println();
        String compareSequence = "GCGUGCUGCUUUAAG";
        AminoAcidLL compareList = AminoAcidLL.createFromRNASequence(compareSequence);
//        while(compareList!=null){
//            System.out.println("Amino: " + compareList.aminoAcid);
//            for(int i = 0; i<compareList.codons.length; i++){
//                System.out.println(compareList.codons[i] + " " + compareList.counts[i]);
//            }
//            compareList = compareList.next;
//        }

        int expected = 6;
        int difference = list.codonCompare(compareList);
        assertEquals(expected, difference);
    }

    public static void main(String[]args){
        // [G, A, C, D, F]
        // [1, 2, 1, 2, 1] amino count
        String sequence = "GGGGCGGCGUGCGACGACUUU";
        AminoAcidLL list = AminoAcidLL.createFromRNASequence(sequence);
        char [] aminos = list.aminoAcidList();
        System.out.println(Arrays.toString(aminos));
        System.out.println(list.isSorted());
        System.out.println(Arrays.toString(list.aminoAcidCounts()));

        System.out.println();
        AminoAcidLL newList = AminoAcidLL.sort(list);
        // [A, C, D, F, G] after sorting it
        // [2, 1, 2, 1, 1] amino count
        char [] aminosSorted = newList.aminoAcidList();
        System.out.println(Arrays.toString(aminosSorted));
        System.out.println(newList.isSorted());
        System.out.println(Arrays.toString(newList.aminoAcidCounts()));

        System.out.println();
        String compareSequence = "GCGUGCUGCUUUAAG";
        AminoAcidLL compareList = AminoAcidLL.createFromRNASequence(compareSequence);
        char [] aminosCompare = compareList.aminoAcidList();
        int [] aminoCount = compareList.aminoAcidCounts();
        System.out.println(Arrays.toString(aminosCompare));
        System.out.println(Arrays.toString(aminoCount));
        System.out.println(compareList.isSorted());

//        int diff = newList.aminoAcidCompare(compareList);
//        System.out.println("Difference: " + diff);

    }
}