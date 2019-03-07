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
    File text = new File("Maze.txt");
    Scanner inf = new Scanner(text);
    String[] one=inf.nextLine().split(" ",-1);
    R=Integer.parseInt(one[0]);
    C=Integer.parseInt(one[1]);
    E=Integer.parseInt(one[2]);
    N=Integer.parseInt(one[3]);
    map=new int[R][C];
    for (int x=0;x<R;x++) {
      one=inf.nextLine().split(" ",-1);
      for (int y=0;y<C;y++ ) {
        map[x][y]=Integer.parseInt(one[y]);
      }
    }
    while(inf.hasNextLine()){
      one = inf.nextLine().split(" ",-1);
      for (int a=0;a<C;a++) {
        instructions[a]=Integer.parseInt(one[a]);
      }
    }
  }
  private static void stomp(int r, int c, int e){
    int highest=0;;
    for (int a=r;a<r+3 ;a++ ) {
      for (int b=c;b<c+3 ;b++ ) {
        if(map[a][b]>highest){
          highest=map[a][b];
        }
      }
    }
    highest=highest-e;
    for (int a=r;a<r+3 ;a++ ) {
      for (int b=c;b<c+3 ;b++ ) {
        map[a][b]=highest;
      }
    }

  }
  private static void depths(){
    for (int a=0;a<R ;a++ ) {
      for (int b=0;b<C ;b++ ) {
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
  public static int bronze(String filename){
    for (int i=0;i<instructions.length/3;i++ ) {
      stomp(instructions[i],instructions[i+1],instructions[i+2]);
    }
    depths();
    return sum()*72*72;
  }
}
