import java.util.*;
import java.io.*;
public class USACO{
  private static int R;
  private static int C;
  private static int E;
  private static int N;
  private static int[][] map;
  private static int[] instructions;
  private static void bronzeFile(String filename)throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String[] one=inf.nextLine().split(" ",-1);
    R=Integer.parseInt(one[0]);
    C=Integer.parseInt(one[1]);
    E=Integer.parseInt(one[2]);
    N=Integer.parseInt(one[3]);
    map=new int[R][C];
    instructions= new int[3*N];
    for (int x=0;x<R;x++) {
      one=inf.nextLine().split(" ",-1);
      for (int y=0;y<C;y++ ) {
        map[x][y]=Integer.parseInt(one[y]);
      }
    }
    int b=0;
    while(inf.hasNextLine()){
      one = inf.nextLine().split(" ",-1);
      for (int a=0;a<3;a++) {
        instructions[3*b+a]=Integer.parseInt(one[a]);
        //System.out.println(instructions[a]);
      }
      b++;
    }
  }
  private static void stomp(int r, int c, int e){
    int highest=0;
    //System.out.println(r+" "+c);
    for (int a=r-1;a<r+2;a++ ) {
      for (int b=c-1;b<c+2;b++ ) {
        if(map[a][b]>highest){
          highest=map[a][b];
        }
      }
    }
    highest=highest-e;
    //System.out.println(highest);
    for (int a=r-1;a<r+2 ;a++ ) {
      //System.out.print("\n");
      for (int b=c-1;b<c+2 ;b++ ) {
        //System.out.print(map[a][b]+" ");
        map[a][b]=highest;
      }
    }

  }
  private static void depths(){
    for (int a=0;a<R ;a++ ) {
      System.out.print("\n");
      for (int b=0;b<C ;b++ ) {
        System.out.print(map[a][b]+" ");
          map[a][b]=E-map[a][b];

      }
    }
  }
  private static int sum(){
    int summation=0;
    for (int a=0;a<R ;a++ ) {

      for (int b=0;b<C ;b++ ) {

          if(map[a][b]>0){
            summation+=map[a][b];
          }
      }
    }
    return summation;
  }
  public static int bronze(String filename)throws FileNotFoundException{
    bronzeFile(filename);
    for (int i=0;i<instructions.length/3;i++ ) {
      //System.out.println(instructions[3*i]+","+instructions[3*i+1]+","+instructions[3*i+2]);
      stomp(instructions[3*i],instructions[3*i+1],instructions[3*i+2]);
    }
    depths();
    //System.out.println(sum());
    return sum()*72*72;
  }
  public static void main(String[] args) {
    try{
    System.out.println(bronze("Sample.txt"));
  }
  catch(FileNotFoundException e){
    System.out.println("file not found");
  }
  }
}
