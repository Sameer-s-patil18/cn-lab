package pkg;
import java.util.*;
public class Ford {
	private int D[];
	private int num_ver ;
	public static final int MAX_VALUE = 999 ;
	public Ford(int num_ver) {
		this.num_ver = num_ver ;
		D = new int[num_ver + 1];
	}
	
	public void BellmanFord(int src, int A[][]) {
		for(int n=1; n <= num_ver ; n++) {
			D[n] = MAX_VALUE ;
		}
		D[src] = 0 ;
		for(int n=1; n<=num_ver-1;n++) {
			for(int sn=1 ; sn<=num_ver ;sn++) {
				for(int dn=1;dn<=num_ver ;dn++) {
					if(A[sn][dn] != MAX_VALUE) {
						if(D[dn] > D[sn] + A[sn][dn]) D[dn] = D[sn] + A[sn][dn];
					}
				}
			}
		}
		
		for(int sn=1;sn<= num_ver ; sn++) {
			for(int dn = 1;dn <= num_ver ; dn++) {
				if(A[sn][dn] != MAX_VALUE) {
					if(D[dn] > D[sn] + A[sn][dn]) System.out.println("the graph cntains negative edge cycle ");
				}
			}
		}
		
		for(int v=1 ; v<= num_ver ; v++) {
			System.out.println("distance of src " + src + "to dest " + D[v]);
		}
	}
	
	public static void main(String[] args) {
		int num_ver = 0 , src ;
		Scanner in = new Scanner(System.in);
		System.out.println("enter the no of vertices ");
		num_ver = in.nextInt();
		int A[][] = new int[num_ver + 1][num_ver + 1];
		System.out.println("enter the adj matrix");
		for(int sn=1 ; sn<= num_ver ; sn++) {
			for(int dn=1 ; dn<= num_ver ; dn++) {
				A[sn][dn] = in.nextInt();
				if(sn == dn) {
					A[sn][dn] = 0 ;
					continue ;
				}
				if(A[sn][dn] == 0) {
					A[sn][dn] = MAX_VALUE ;
				}
			}
		}
		System.out.println("enter the src vertex");
		src = in.nextInt();
		Ford b = new Ford(num_ver);
		b.BellmanFord(src, A);
		in.close();
		
	}
}
