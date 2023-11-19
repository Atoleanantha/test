package pass1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class PassOne {
    public static void main(String[] args) {
        BufferedReader br=null;
            FileReader fr=null;

            BufferedWriter bw=null;
            FileWriter fw=null;
        try {
            // Input and output file paths
            String inputFilePath = "C:\\Users\\atole\\OneDrive\\Documents\\spos practicals\\practical\\pass1\\input.txt";
            String outputFilePath = "C:\\Users\\atole\\OneDrive\\Documents\\spos practicals\\practical\\pass1\\output.txt";
            

            fr=new FileReader(inputFilePath);
            br=new BufferedReader(fr);

            fw= new FileWriter(outputFilePath);
            bw=new BufferedWriter(fw);

            

            //imperative
            HashMap<String, String> is=new HashMap<>();
            is.put("STOP","00");
            is.put("ADD","01");
            is.put("SUB","02");
            is.put("MULT","03");
            is.put("MOVER","04");
            is.put("MOVEM","05");
            is.put("COMP","06");
            is.put("BC","07");
            is.put("DIV","08");
            is.put("READ","09");
            is.put("PRINT","10");

            //directive
            HashMap<String,String> ad=new HashMap<>();
            ad.put("START", "01");
            ad.put("END", "02");
            ad.put("ORIGIN", "03");
            ad.put("EQU", "04");
            ad.put("LTORG", "05");

            //declarative
            HashMap<String,String> dl=new HashMap<>();
            dl.put("DC","01");
            dl.put("DS", "02");
    
            //registers
            HashMap<String,String> reg=new HashMap<>();
            reg.put("AREG", "01");
            reg.put("BREG", "02");
            reg.put("CREG", "03");
            reg.put("DREG", "04");

            Hashtable<String,String> symTab=new Hashtable<String,String>();
            Hashtable<String,String> ltTab=new Hashtable<String,String>();
            int ltptr=1;
            int symptr=1;
            int locptr=0;

            String currentLine=br.readLine();
            String s=currentLine.split(" ")[1];

            if(s.equals("START")){
                bw.write("AD\t 01");
                String s1=currentLine.split(" ")[2];
                bw.write("C\t "+s1+"\n");
                locptr=Integer.parseInt(s1);
            }

            while ((currentLine=br.readLine())!=null) {

                boolean flag=false;
                
                s=currentLine.split(" |\\,")[0];

                for(Map.Entry e: symTab.entrySet()){
                    if(s.equals(e.getKey())){
                        e.setValue(String.valueOf(locptr));
                        flag=true;
                        break;
                    }
                }
                
                if(s.length() !=0 && flag==false){
                    symTab.put(s,String.valueOf(locptr));
                    symptr++;
                }
                
                boolean isOpcode=false;
                String type=null;
                s=currentLine.split(" |\\,")[1];
                for(Map.Entry e:is.entrySet()){
                    if(s.equals(e.getKey())){
                        bw.write("IS\t "+ e.getValue()+"\t");
                        isOpcode=true;
                        type="is";
                        break;
                    }
                }
                if(!isOpcode){
                    for(Map.Entry e:ad.entrySet()){
                        if(s.equals(e.getKey())){
                            bw.write("AD\t "+e.getValue()+"\t");
                            type="ad";
                            isOpcode=true;
                            
                        }
                    }
                }
                if(!isOpcode){
                    for(Map.Entry e:dl.entrySet()){
                        if(s.equals(e.getKey())){
                            bw.write("DL\t"+e.getValue()+"\t");
                            type="dl";
                            isOpcode=true;
                    
                        }
                    }
                }
                boolean mind_the_lc=false;
                if(s.equals("END")){
                    for(Map.Entry e:ltTab.entrySet()){
                        if(e.getValue()==""){
                            e.setValue(locptr);
                            locptr++;
                            mind_the_lc=true;
                        }
                    }
                }

                if(s.equals("EQU")){
                    symTab.put(s, String.valueOf(locptr));
                }

                if(currentLine.split(" |\\,").length >2){
                    s=currentLine.split(" |\\,")[2];
                    for(Map.Entry e: reg.entrySet()){
                        if(s.equals(e.getKey())){
                            bw.write(e.getValue()+"\t");
                            isOpcode=true;
                        }
                    }
                    if(type=="dl"){
                        bw.write("C\t"+ s +"\t");
                    }else{
                        symTab.put(s, "");
                    }
        
                }
                if(currentLine.split(" |\\,").length>3){
                    s=currentLine.split(" |\\,")[3];

                    if(s.contains("=")){
                        ltTab.put(s, "");
                        bw.write("L\t "+ltptr+"\t");
                        isOpcode=true;
                        ltptr++;
                    }else{
                        symTab.put(s, "");
                        bw.write("S\t"+symptr+"\t");
                        symptr++;
                    }
                }
                bw.write("\n");
                if(mind_the_lc==false){
                    locptr++;
                }
                
            }

            String SymTab = "SYMTAB.txt";
            String LitTab = "LITTAB.txt";
            
            fw=new FileWriter(SymTab);
            bw=new BufferedWriter(fw);
            System.out.println("Symbol \t Address");
            for(Map.Entry e:symTab.entrySet()){
                bw.write(e.getKey() +"\t"+e.getValue()+"\n");
                System.out.println(e.getKey() +"\t"+e.getValue());
            }

            fw=new FileWriter(LitTab);
            bw=new BufferedWriter(fw);
            System.out.println("Literal \t Address");
            for(Map.Entry e:ltTab.entrySet()){
                bw.write(e.getKey() +"\t"+e.getValue()+"\n");
                System.out.println(e.getKey() +"\t"+e.getValue());
            }

            br.close();
            bw.close();
            fr.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
