package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import dao.RegEx;
import dao.Session;
import dao.UserRegisterDAO;
import dto.UserDTO;
import dto.UserRegisterDTO;
import view.SgtListView;

public class Test {
	public static void main(String[] args) {
		String pepe="BBBBQBQBBBBBQBBBBBBBBBBBBBBBBBBBBBBBBBQBBBBBQBBBBBBBBBBBBBBBQBBBBBBBBBBBBBBBBBBBBBBBBBQBBBBBBBQBQBBB\r\n" + 
				"BBBBBBBBBBBBBBBQBBBQBBBBBBBQBBBBBBBBBBBQBBBBBQBBBQBBBQBBBQBBBBBQBBBBBBBBBBBQBBBBBBBQBBBBBBBBBBBBBBBB\r\n" + 
				"QBBBBBBBBBQBBBBBBBBBBBBBBBQBBBQBBBBBBBBBBBQBQBBBBBQBBBQBBBQBBBBBBBBBBBBBBBQBBBBBQBBBBBBBBBBBBBBBBBQB\r\n" + 
				"BQBBBBBBBBBBBBBQBBBBBQBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBQBBBBBBBBBQBBBBBQBBBQBBBBBBBBBBBBBBBB\r\n" + 
				"QBBBBBBBBBBBBBQBBBBBBBBBBBBBQBBBBBBBBBBBBBBBBBBBBBBBBBQBBBBBQBBBBBBBQBBBBBBBBBBBBBBBQBQBBBBBBBBBBBBB\r\n" + 
				"BBBBBBBBBQBBBBBBBBBBBQBBBBBBBBBBBBBBBBBBBBBBBQBBBBBBBBBQBBBBBBBBBBBBBBBBBQBBBBBBBBBBBBBBBBBBBBBQBBBB\r\n" + 
				"QBBBBBBBBBBBQBBBBBBBBBBBBBQBBBBBBBQBBBBBBBBBBBBBBBBBBBQBBBBBBBBBBBBBBBBBBBBBBBQBBBBBBBQBQBBBBBBBBBBB\r\n" + 
				"BBBBBBBQBBBBBBBBBBBBBBBBBBBQBggEMQBQBBBBBBBBBBBBBBBQBBBQBBBBBBBBBQBQBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\n" + 
				"QBBBBBBBBBBBQBBBQBBBBBQPr.           :LQBBBBBBBBBBBBBBQbUJ777j2DBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\n" + 
				"BBBBBQBBBBBBBBBBBQBQ2     ..:::::::..    :gBBBBBBBKi              iMBBBBBBBBBBBBBBBBBQBBBBBBBBBBBBBB\r\n" + 
				"BBQBQBBBBBBBBBBBQB:   .:::::::::::::::::.   sBBv     .::::::::::..   BBBBBBBBBBBBBQBBBQBBBBBBBBBBBQB\r\n" + 
				"BBBBBBBBBBBBBBBBi  .i::::::::::::::::::::::      :::::::::::::::::::  bBBBBBBQBBBBBBBQBBBBBBBBBBBQBB\r\n" + 
				"QBBBBBBBQBBBBBP  ::::::::::iiiii:::::iirii::i. .i::::::::::::::::::::. gBBBBBBBBBBBBBBBBBBBBBBBBBBBB\r\n" + 
				"BBBQBBBBBBBBBr  i:::::::ri:.             .:irr: i:::::i:i:iiiii:i:i:i:  BQBBBBBBBBBQBBBQBBBBBBBBBBBB\r\n" + 
				"BBBBBBBBBBBB: :::::::ii:     .....           .v  7i:::.............:::r sBBBBBBBBBBBBBBBBBBBBBBBQBBB\r\n" + 
				"BBBQBBBQBBBL ::::::::.   :i.    ..::i::                                  BBBBBBBBBBBBBBBBQBBBBBBBQBB\r\n" + 
				"QBBBBBBBQBd .::::::::..r:  .YMQBBBBBBBBBBBB2:     .7riiriri.                :XBBBBBBBBBBBBBBBBQBBBBB\r\n" + 
				"BBBBBBBBBB  :::::::::ii  7BBBQBBBBB2. ..7gBBBBQ7    :ir:i   rXMBBBBQBBBgPv.    .BBBBBQBBBBBBBBBBBBBB\r\n" + 
				"QBBBBBQBB  i:::::::::. iBBBBQBQBBB          dBBBBM.    r  QBQBBBBBQBQBBBBBBBQQr  1BBQBBBBBBBBBBBBBQB\r\n" + 
				"BQBBBBBB. i:::::i:.   PBBBBQBQBQB7       i    BBBBBQ.    QBBBBBBBQBBB.    :BBBBBP :QBBBBBBBBBBBBBBBB\r\n" + 
				"BBBBB7   ::::::     vBBBBBBBBBBBB        B     BBBBBB5  dBBBBBBBBBQ7         BBBQB..BBBBBBBBQBBBBBQB\r\n" + 
				"BBB.  .  i::::: KBBBBBBBBBBBBBBBB    :K        BBBBBBBB XQBBBQBBBQD      YQ   BQBBB.:BBBBBBBBBBBBBBB\r\n" + 
				"Bg  :ii  r:::::   rBBBBBBBBBBBBBBB            qBBBBBBBBP BBBBBBBBB2    7      .BBBBB BBBBBBBBBBBBBBB\r\n" + 
				". .:::r  i::::::i.  BBBBBBBBBQBBBBB:         bBBBBBBBBBB.YBQBBBBBBB    .      iQBQBB 1BQBQBBBQBBBBBB\r\n" + 
				" i::::: .::::::::i.  BBBBBBBBBBBBBBBQBZjrrJBBBBBBBBBBBBB1 BBBBBBBBBBJ         BBBBBB BBBBBBBBBQBBBBB\r\n" + 
				"ii::::::::::::::::i:  .QBBBBBQBQBQBBBBBBBBBBBBBBBBBBBBBBL.BBBBBQBQBBBBgr   .PBBBBBB.:QBBBBBBBBBBBBBB\r\n" + 
				".i:::::::::::::::::ii    2QBBBBBBBBBBBBBQBQBBBBBBBBBBBBB gBBQBBBBBBBBBBBBBBBBBBBBBr BBBBQBBBBBQBBBBB\r\n" + 
				":i:.:::::::::::::::::i:     rPBBBBBBBBBQBBBBBBBBBBBBBQB iQBQBBBBBBBBBQBBBBBBBQBBB. BBQBBBBBBBBBBBBBB\r\n" + 
				".i.::::::::::::::::::::r:.      .rIZQBBBBBQBBBQBBBBBQv   BBBBBBBBBBBQBBBBBBBBBBi rQBQBBBBBBBBBBBBBBB\r\n" + 
				":i:::::::::::::::::::::::ii:.           .:irv77r:.    .i .BBBBBBBBBBBBBBBBBBS   gQBBBBBQBBBBBBBBBQBB\r\n" + 
				".i:::::::::::::::i:i:::::::::iii..                 .:::::   i1PMQBBBBQZqv:    jBBBQBBBQBBBBBBBBBBBBB\r\n" + 
				":i:::::::::::::i:...ii::::::::::::iii::....  .i::::::::::.                 sBQBBBQBBBBBBBBBBBBBBBQBB\r\n" + 
				".i.::::::::::ii       ii:::::::::::::::::iiii:::::::::::::i:.           .:  ZBBBBBBBBBBBBBBBBBQBBBBB\r\n" + 
				":i:::::::::::i  ::i:.  :r:::::::::::::::::::::::::::::::::::iir. .riiirii:i.  BBBQBBBBBQBBBBBBBQBBBB\r\n" + 
				".i:::::::::::: :::.::i   iri:::::::::::::::::::::::::::::::::::iii:::::::::::  BBBBBBBBBBBBBBBBBBBBB\r\n" + 
				":i:::::::::::: ::.:..:i:   .rii::::::::::::::::::::::::::::::::::::::::::::::i  BBBQBQBBBBBBBBBBBBBB\r\n" + 
				":7i::::::i:::i  :::.  .ii:    .iri::::::::::::::::::::::::::::::::::::::::::::: LBBBBBBBBBBBBBBBBBBB\r\n" + 
				"7. ::::i:.i::i. .::::   :ii:.     :irii:::::::::::::::::::::::::::::::::::::::i. BBBBQBBBBBQBQBBBBBB\r\n" + 
				"    i:i.   r:r7:  i::i:   .iii:.      .:iiii:i:::::::::::::::::::::::::::::iiri: sBBBBBBBBBBBBQBBBBB\r\n" + 
				" i. rr. :. rr      .i::::    .::irr:.       .:iiririi:i:::::::::::i:iiiiii:.       BBBBBBBBBBBBBBBBQ\r\n" + 
				" 7: r. .7. L  :: 7   .:i:i:.       .:rri:.         ....::::::::::...          .::: ZBBBBQBBBBBBBBBBB\r\n" + 
				":ri   .ir    ri: :ri    .:iii::...     .:iiiri:..                       ..:iiiri. iBBBBBBQBBBBBBBBBB\r\n" + 
				":r:i .i:i   :i:: :::ir.     .ii::::i:.        ..::iiiiiriiiiiiiiiririiii::...     BBMBBBBBBBBBBBBBBB\r\n" + 
				":i::i:::i.  r::i .i:::iii:.    .::::::i::.                                     .i     BBBQBQBBBBBBBQ\r\n" + 
				".i:::::::i.::::r  i::::::::ri:       ..::iiririi::::...................::::iir .ii.  SBBQBBBBBBBBBQB\r\n" + 
				":i::::::::i::::i  rii:::::::::rii..             ....::::::i:i:iiiiiiiir:i::.   :   .BBBBBBBBBBBBBBBQ\r\n" + 
				"i7::::::::::::::i   .::iii:::::::i:iirii:...                                     UBBBBBBBBBBBBQBBBBB\r\n" + 
				" .r::::::::::::::i:       :i::::::::::::::iiiiiiriiiiii::::.:.:.::i.   .::i:  UBBBBBBBBBBBBBBBBBBBBB\r\n" + 
				"   ri::::::::::::::iii::.   i::::::::::::::::::::::::::::::::rr7:.   :rri7: 1BBBBBBBBBQBBBQBBBBBBBBB\r\n" + 
				"    :rr:::::::::::::::::ii:  i::::::::::::::::::::::::::::irr:    .i7irri   BBBQBBBBBBBBBBBBBBBQBBBB\r\n" + 
				"       :ri::::::::::::::::i: r:i:::::::::::::::i:i:i:ii7i..    :rvrri:.     BBQBBBQBBBBBBBBBBBBBBBBB\r\n" + 
				"         ir:::::::::::::::i. .:::::::::::::::::........  P                 .BBBBBQBQBBBBBBBBBBBBBBBB\r\n" + 
				"           7i:::::::::::::r  .                   . .     v                 iBBBBBBQBBBBBQBBBBBQBBBBB\r\n" + 
				"            .iriiii:iiiir7. .:::::iiiiriiiiii:::..                         sBBBBBBBQBBBQBQBBBQBBBBBB\r\n" + 
				"                ...:...                                                    gBBBBBBBBBBBBBBBBBBQBBBBB\r\n" + 
				"                                                                           BBBBBBBBBBBBBBBBBBBBQBBBQ\r\n" + 
				"                                                         .                .BBBBBBBQBBBBBBBBBQBBBBBBB";
		System.out.println(pepe);

	}
}
