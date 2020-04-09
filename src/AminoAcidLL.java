import java.util.Arrays;

class AminoAcidLL{

  char aminoAcid;
  String[] codons;
  int[] counts;
  AminoAcidLL next;

  public AminoAcidLL(){
  }
  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon){
      aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
      codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
      counts = new int[codons.length];
      next = null;
      incrementCodonCounter(inCodon);
  }

  private void incrementCodonCounter(String inCodon){
    for(int i=0; i<codons.length; i++){
      if(codons[i].equals(inCodon.toUpperCase())){
        counts[i]++;
      }
    }
  }
  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  public void addCodon(String inCodon){
    char aminoChar = AminoAcidResources.getAminoAcidFromCodon(inCodon);
//    System.out.println("amino: " + this.aminoAcid);
    if(aminoChar == this.aminoAcid){
      incrementCodonCounter(inCodon);
    }else if(next != null){
      next.addCodon(inCodon);
    }else{
      next = new AminoAcidLL(inCodon);
    }
  }

  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int ctr = 0;
    for(int i = 0; i < this.counts.length; i++){
      ctr+=counts[i];
    }
    return ctr;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts.
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    int diff = 0;
    return diff;
  }
  public AminoAcidLL findAmino(AminoAcidLL head, char amino){
    if(head.aminoAcid == amino){
      return head;
    }else if(head.next != null){
      findAmino(head.next, amino);
    }
    return null;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    return 0;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
//    if(this.next == null){
//      return new char[]{this.aminoAcid};
//    }
//    char [] aminos = next.aminoAcidList();
//    char [] rtn = new char[aminos.length+1];
//    for(int i=0;i<aminos.length;i++) {
//      rtn[i+1] = aminos[i];
//    }
//    rtn[0] = aminoAcid;
//    return rtn;
      return aminoAcidList(0);
  }

  public char[] aminoAcidList(int num){
      if(next == null){
          char [] arr = new char[num+1];
          arr[num] = aminoAcid;
          return arr;
      }
      char [] arr = next.aminoAcidList(num+1);
      arr[num] = aminoAcid;
      return arr;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    return aminoAcidCounts(0);
  }
  public int[] aminoAcidCounts(int num){
    if(this.next == null){
      int [] arr = new int[num+1];
      arr[num] = this.totalCount();
      return arr;
    }
    int [] arr = this.next.aminoAcidCounts(num+1);
    arr[num] = this.totalCount();
    return arr;
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    if(this.aminoAcid > this.next.aminoAcid){
      return false;
    }else if(this.next.next!=null) {
      this.next.isSorted();
    }
    return true;
//    AminoAcidLL current = this;
//    while(current.next!=null){
//      if(current.aminoAcid > current.next.aminoAcid){
//        return false;
//      }
//      current = current.next;
//    }
//    return true;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    // first check if codon is valid
    AminoAcidLL list = null;
    if(AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0,3)) != '*'){
      list = new AminoAcidLL(inSequence.substring(0,3));
      for(int i = 3; i < inSequence.length()-2; i=i+3) {
        if (AminoAcidResources.getAminoAcidFromCodon(inSequence.substring(0, 3)) != '*') {
          list.addCodon(inSequence.substring(i, i + 3));
        }
      }
    }
    if(list == null) System.out.println("List is null");
    return list;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  // Remove static
  public static AminoAcidLL sort(AminoAcidLL inList){

    if(inList ==  null || inList.next == null){
      return inList;
    }

    AminoAcidLL middlePointer = findMiddle(inList);
    AminoAcidLL nextOfMiddle = middlePointer.next;

    middlePointer.next = null;

    AminoAcidLL left = sort(inList);
    AminoAcidLL right = sort(nextOfMiddle);

    AminoAcidLL sortedList = merge(left, right);
    return  sortedList;
  }
  private static AminoAcidLL merge(AminoAcidLL left, AminoAcidLL right){

    if(left == null){
      return right;
    }
    if(right == null){
      return left;
    }
    AminoAcidLL result = null;
    if(left.aminoAcid <= right.aminoAcid){
      result = left;
      result.next = merge(left.next, right);
    }
    else{
      result = right;
      result.next = merge(left, right.next);
    }
    return result;
  }
  private static AminoAcidLL findMiddle(AminoAcidLL head){
    if(head == null){
      System.out.println("Couldn't find middle = head == null");
      return head;
    }
    AminoAcidLL slow = head, fast = head;
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}

//  /* Recursive method that finds the differences in **Amino Acid** counts.
//   * the list *must* be sorted to use this method */
//  public int aminoAcidCompare(AminoAcidLL inList){
//    int diff = 0;
//
//    // if the comparison list is empty, the difference is the sum of the rest of the lists' counts
//    if(inList == null){
//      diff += this.totalCount();
//      if(this.next != null){
//        diff += this.next.aminoAcidCompare(inList);
//      }
//    }
//
//    // the two nodes match, add the difference in counts
//    // from the rest of the list to this one and return the total.
//    else if(this.aminoAcid == inList.aminoAcid){
//      diff = Math.abs(this.totalCount()-inList.totalCount());
//      if(this.next != null && inList.next != null){
//        diff += this.next.aminoAcidCompare(inList.next);
//      }if(this.next == null){
//        diff += this.aminoAcidCompare(inList.next);
//      }
//
//      // need to find out if something later in my list matches the current,
//      // but since I don't have a match my total count gets added to the difference
//    }else if(this.aminoAcid < inList.aminoAcid){
//      diff += this.totalCount();
//      if(this.next != null){
//        return this.next.aminoAcidCompare(inList);
//      }
//
//      // need to find out if something later in *their* list matches me,
//      // but since they don't have a match in my list their total count gets added to the difference
//      // also if I don't have anything else keep adding their total to the difference
//    }else if(this.next == null || this.aminoAcid > inList.aminoAcid){
//      diff += inList.totalCount();
//      if(inList.next != null){
//        return this.aminoAcidCompare(inList.next);
//      }
//    }
//    return diff;
//  }


//  /* Recursive method that finds the differences in **Amino Acid** counts.
//   * the list *must* be sorted to use this method */
//  public int aminoAcidCompare(AminoAcidLL inList){
//    int diff = 0;
//    if(this == null && inList == null){
//      return 0;
//    }
//    if(this.aminoAcid == inList.aminoAcid){
//      System.out.println(Math.abs(this.totalCount() - inList.totalCount()));
//      diff+=Math.abs(this.totalCount() - inList.totalCount());
//      if(this.next==null){
//        return 0;
//      }
//      if(this.next!=null && inList.next!=null){
//        diff+=this.next.aminoAcidCompare(inList.next);
//      }
//    }
//    AminoAcidLL aminoFound = findAmino(inList, this.aminoAcid);
//    // if amino 'this' is not in 'inList' add this.totalCount to diff
//    System.out.println(aminoFound);
//    if(aminoFound == null){
//      System.out.println("if " +this.totalCount());
//      diff+=this.totalCount();
//      if(this.next!=null){
//        this.next.aminoAcidCompare(inList);
//      }
//    }
//    diff+=inList.totalCount();
//    return diff;
//  }