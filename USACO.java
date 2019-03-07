import java.util.*;
import java.io.*;
public class USACO{
  private int R;
  private int C;
  private int E;
  private int N;
  private int[][] map;
  private int[] instructions;
  private void bronzeFile(String filename)throws FileNotFoundException{
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
  public static int bronze(String filename){
    return 5;
  }
}
