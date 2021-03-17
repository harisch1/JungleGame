package hk.edu.polyu.comp.comp2021.jungle.model;

 enum rank {
    Rat(1),Cat(2),Dog(3),Wolf(4),Leopard(5),Tiger(6),Lion(7),Elephant(8);

    private int rank;

     rank(int rank){
         this.rank=rank;
     }

     public int getRank(){
         return this.rank;
     }
}
