package slideSender;
import java.net.*;
import java.io.* ;

import java.net.ServerSocket;

public class slideSender {
	@SuppressWarnings({"deprecation","deprecation","deprecation"})
	public static void main(String[] args)throws Exception {
		ServerSocket ser = new ServerSocket(4000);
		Socket s = ser.accept();
		DataInputStream in = new DataInputStream(System.in);
		DataInputStream in1 = new DataInputStream(s.getInputStream());
		String[] sbuff = new String[8];
		PrintStream p ;
		int sptr = 0,sws = 8 ,nf,ano,i ;
		String ch ;
		do {
			p = new PrintStream(s.getOutputStream());
			System.out.println("enter the no of frames");
			nf = Integer.parseInt(in.readLine());
			p.println(nf);
			if(nf <= sws-1) {
				System.out.println("enter " + nf + "to be sent");
				for(i=1;i <= nf ;i++) {
					sbuff[sptr] = in.readLine();
					p.println(sbuff[sptr]);
					sptr = ++sptr%8 ;
				}
				sws -= nf ;
				System.out.println("ack recd ");
				ano = Integer.parseInt(in1.readLine());
				System.out.println(" for " + ano + " frames");
				sws += nf ;
				
			}else {
				System.out.println("the no of frames exceeds window size ");
				break ;
				
			}
			System.out.println("\n do u want to send more frames \n");
			ch = in.readLine();
			p.println(ch);
		}while(ch.equals("yes"));
		s.close();
	}
}
