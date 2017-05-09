import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorld{

	public static void main(String[] args) throws IOException {

		    BufferedWriter output = null;
	        try {
	            File file = new File("T_SM_CopyPaste.txt");
	            output = new BufferedWriter(new FileWriter(file));
	            for(int i = 0; i<=12; i++){
					output.write(genstring2(i));
				}
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            output.close();
	          }
	        }
		
		//IMPORTANT: Maake sure you are using the correct function below for output
		
	}
	static String genstring(int i){
		StringBuffer sb = new StringBuffer();
		sb.append("<<if $hand.length gt " + i + ">>\\\n");
			sb.append("<<set $itemIter to $hand["+i+"]>>\\\n");
			sb.append("<<print $itemIter[0]>>\n");
			sb.append("<<if $handInteraction is true>>\\\n");
				sb.append("<<if ($inBattle is true) and ($itemIter[5] is \"battleitem\")>>\\\n");
					sb.append("[[use|passage()][$tempStats[2] = $tempStats[2]+$hand["+i+"].get(11); $tempStats[3] = $tempStats[3]+$hand["+i+"].get(12); $tempStats[4] = $tempStats[4]+$hand["+i+"].get(13); $removedInv.add($hand["+i+"].get(0)); $hand.removeObj($hand["+i+"])]]\n");
				sb.append("<<endif>>\\\n");
				sb.append("<<if $itemIter[5] is \"food\">>\\\n");
					sb.append("[[eat|passage()][$HP = $HP + $hand["+i+"].get(9); $removedInv.add($hand["+i+"].get(0)); $hand.removeObj($hand["+i+"])]]\n");
					sb.append("<<endif>>\\\n");
				sb.append("<<if ($inBattle is false)>>\\\n");
					sb.append("[[put away|passage()][$hand.removeObj($hand["+i+"])]]\n");
				sb.append("<<else>>\\\n");
					sb.append("<<if $itemIter[6] is true>>\\\n");
						sb.append("<div class=\"throwawaywarning\">[[throw away|passage()][$removedInv.add($hand["+i+"].get(0)); $hand.removeObj($hand["+i+"])]]</div>\n");
					sb.append("<<endif>>\\\n");
				sb.append("<<endif>>\\\n");
			sb.append("<<endif>>\\\n");
		sb.append("<<endif>>\\\n");
		sb.append("\n");
		
		return sb.toString();
	}
	
	static String genstring2(int i){
		StringBuffer sb = new StringBuffer();
		sb.append("<<if $sellHand.length gt " + i + ">>\\\n");
			sb.append("<<set $itemIter to $sellHand["+i+"]>>\\\n");
			sb.append("<<print $itemIter[0]>>\n");
			sb.append("<<set $cashForSelling to convertCur($itemIter[8])>>\\\n");
			sb.append("I'll buy this off of you for:\n");
				sb.append("<<if $cashForSelling[2] gt 0>>\\\n");
					sb.append("<<$cashForSelling[2]>> Gold\n");
				sb.append("<<endif>>\\\n");
				sb.append("<<if $cashForSelling[1] gt 0>>\\\n");
					sb.append("<<$cashForSelling[1]>> Silver\n");
				sb.append("<<endif>>\\\n");
				sb.append("<<$cashForSelling[0]>> Copper\n");
			sb.append("[[Sell|passage()][$Copp = $Copp + convertCur($sellHand["+i+"].get(8)).get(0); $Silv = $Silv + convertCur($sellHand["+i+"].get(8)).get(1); $Gold = $Gold + convertCur($sellHand["+i+"].get(8)).get(2); $removedInv.add($sellHand["+i+"].get(0)); $hand.removeObj($sellHand["+i+"])]]\n");
		sb.append("<<endif>>\\\n\n");
		
		return sb.toString();
	}
	
}

