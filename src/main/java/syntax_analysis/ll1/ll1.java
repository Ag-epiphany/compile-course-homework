package syntax_analysis.ll1;
import syntax_analysis.ll1.Adapter.LL1Error;
import syntax_analysis.ll1.util.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static constant.ProjectConstant.*;
import static syntax_analysis.ll1.Adapter.TokenListAdapter.getTokens;
import static syntax_analysis.ll1.Adapter.TokenListAdapter.getTokensLine;

public class ll1 {
    private static Node nodes = new Node("Program");
    private static LL1Error error;
    private static HashMap<String,ArrayList<String>> comlpeteProduction = new HashMap<>();
    private static ArrayList<String> non_terminal = new ArrayList<>();
    private static ArrayList<String> terminal = new ArrayList<>();

    // 存储非终止节点的 first 集
    private static HashMap<String,HashMap<String,String>> first = new HashMap<>();
    // 存储非终止节点的 follow 集
    private static HashMap<String,HashMap<String,String>> follow = new HashMap<>();
    private static Stack<String> inputStack = new Stack<>();
    private static Stack<Integer> inputStackLine = new Stack<>();
    private static Set<String> nowNodes = new HashSet<>();

    public static void readProduction(){
        // 从文件中读取文法
        InputStream inputStream = ll1.class.getResourceAsStream(DEFAULT_SYNTAX_GRAMMAR_FILEPATH);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(" ");
                    //区分每条产生式中的各个 Token 是否是终止字符，并存储
                    for(String part : parts){
                        if(!part.equals("::=")&&!part.equals("|")){
                            if(!terminal.contains(part)&&isTerminal(part)){
                                terminal.add(part);
                            }
                            else {
                                if(!non_terminal.contains(part)&&!terminal.contains(part)){
                                    non_terminal.add(part);
                                }

                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Unable to find the file.");
        }



    }

    public static void CompleteProduction(){

        InputStream inputStream = ll1.class.getResourceAsStream(DEFAULT_SYNTAX_GRAMMAR_FILEPATH);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    ArrayList<String> temp = new ArrayList<>();
                    for(int j = 1; j < parts.length ;j++){
                        if(!parts[j].equals("::=")){
                            temp.add(parts[j].trim());
                        }
                    }

                    String[] part = parts[0].split("::=");
                    for(int j = 1; j < part.length;j++){
                        temp.add(part[j].trim());
                    }
                    comlpeteProduction.put(part[0].trim(),temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Unable to find the file.");
        }

    }

    // 判断一个字符是不是终止字符
    public static boolean isTerminal(String str){
        if(str.equals("(")||str.equals(")")||str.equals("@")||str.equals(",")
                ||  str.equals("*")
                ||  str.equals("/")
                ||  str.equals("-")
                ||  str.equals("+")
                ||  str.equals("<")
                || str.equals(";")
                || str.equals("=")
                || str.equals("[")
                || str.equals("]")
                ||  str.equals(".")
                || str.equals(":=")
                || str.equals("..")
        )
            return true;

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void getFirst(String nonterminal){

        ArrayList<String> completeTokens = comlpeteProduction.get(nonterminal);

        HashMap<String,String> firstSet = new HashMap<>();
        if(completeTokens!=null){
            for(String t : completeTokens){

                String[] subtonken = t.split(" ");

                if(subtonken.length > 1){
                    for(String tt : subtonken){
                        if(tt.equals("@")||terminal.contains(tt)){
                            firstSet.put(tt,t);
                            break;
                        }
                        else{
                            HashMap<String, String> tempfirstset = first.get(tt);
                            if(tempfirstset != null && tempfirstset.size() > 0){
                                if(!tempfirstset.containsKey("@")||tt.equals(subtonken[subtonken.length-1])){
                                    for(String a : tempfirstset.keySet()){
                                        firstSet.put(a,t);
                                    }
                                    break;
                                }
                                else if(tempfirstset.containsKey("@")&&!tt.equals(subtonken[subtonken.length-1])){
                                    for(String a : tempfirstset.keySet()){
                                        if(!a.equals("@")){
                                            firstSet.put(a,t);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                else{
                    String tt = subtonken[0];
                    if(tt.equals("@")||terminal.contains(tt)){
                        firstSet.put(tt,t);
                    }
                    else{
                        HashMap<String, String> tempfirstset = first.get(tt);
                        if(tempfirstset != null && tempfirstset.size() > 0){
                            if(!tempfirstset.containsKey("@")||tt.equals(subtonken[subtonken.length-1])){
                                for(String a : tempfirstset.keySet()){
                                    firstSet.put(a,tt);
                                }
                            }
                            else if(tempfirstset.containsKey("@")&&!tt.equals(subtonken[subtonken.length-1])){
                                for(String a : tempfirstset.keySet()){
                                    if(!a.equals("@")){
                                        firstSet.put(a,tt);
                                    }
                                }
                            }
                        }
                    }

                }

                HashMap<String, String> tempset = first.get(nonterminal);
                if(tempset == null){
                    first.put(nonterminal,firstSet);
                }
                else{
                    for(String a : firstSet.keySet()){
                        if(!tempset.containsKey(a)){
                            tempset.put(a,firstSet.get(a));
                        }
                    }
                    first.put(nonterminal,tempset);
                }

            }
        }



    }

    public static void showFirst(){
        readProduction();
        CompleteProduction();
        for(int i = 0; i < non_terminal.size(); i++){
            for(String t : non_terminal){
                getFirst(t);
            }
        }
    }
    public static void getFollow(String nonterminal){
        // 拿到非终止符的产生式的右侧
        ArrayList<String> rightpart = comlpeteProduction.get(nonterminal);
        if(rightpart == null || rightpart.size() < 0) return;


        for(String t : rightpart){
            // 获取当前产生式 右侧 的所有子式
            String[] subtoken = t.split(" ");
            if(subtoken.length > 1){
                for(int i = 0; i < subtoken.length; i++){
                    if(non_terminal.contains(subtoken[i])){
                        HashMap<String,String> followSet = new HashMap<>();
                        if(subtoken[i].equals("Program")){
                            followSet.put("RETURN","Program");
                        }
                        if(!terminal.contains(subtoken[i])){
                            for (int j = i+1; j < subtoken.length; j++) {

                                if(terminal.contains(subtoken[j])){
                                    followSet.put(subtoken[j],subtoken[i] );
                                }
                                else{
                                    HashMap<String, String> tempfirst = first.get(subtoken[j]);
                                    for(String s : tempfirst.keySet()){
                                        if(!s.equals("@")){
                                            followSet.put(s,subtoken[i]);
                                        }
                                    }

                                    if(tempfirst.containsKey("@")){
                                        HashMap<String, String> tempfollow = follow.get(nonterminal);

                                        if(tempfollow!=null){
                                            for(String s : tempfollow.keySet()){
                                                if(s!=null&&!s.equals("@")){
                                                    followSet.put(s,subtoken[i]);
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        if( i == subtoken.length - 1 && non_terminal.contains(subtoken[i])){
                            HashMap<String, String> tempfollow = follow.get(nonterminal);

                            if(tempfollow!=null){
                                for(String s : tempfollow.keySet()){

                                    if(nonterminal.equals(subtoken[i])){
                                        followSet.put(s,"@");
                                    }
                                    else if(!s.equals("@")){
                                        followSet.put(s,subtoken[i]);
                                    }
                                }
                            }
                        }

                        HashMap<String, String> tempp = follow.get(subtoken[i]);
                        if(tempp == null){
                            follow.put(subtoken[i],followSet);
                        }
                        else{
                            for(String a : followSet.keySet()){
                                if(!tempp.containsKey(a)){
                                    tempp.put(a,followSet.get(a));
                                }
                            }
                            follow.put(subtoken[i],tempp);
                        }

                    }
                }

            }
            else{
                HashMap<String, String> followset = new HashMap<>();
                String tt = subtoken[0];
                if(!terminal.contains(tt)){
                    if(tt.equals("Program")){
                        followset.put("RETURN","Program");
                    }
                    HashMap<String, String> tempfollow = follow.get(nonterminal);
                    if(tempfollow!=null){
                        for(String temp : tempfollow.keySet()){
                            followset.put(temp,nonterminal);
                        }
                    }
                    HashMap<String, String> tempp = follow.get(tt);
                    if(tempp == null){
                        follow.put(tt,followset);
                    }
                    else{
                        for(String a : followset.keySet()){
                            if(!tempp.containsKey(a)){
                                tempp.put(a,followset.get(a));
                            }
                        }
                        follow.put(tt,tempp);
                    }

                }

            }

        }
    }
    public static void showfollow(){
        readProduction();

        for(int i = 0; i < non_terminal.size(); i++){
            for(String t : non_terminal){
                getFirst(t);
            }
        }

        for(int i = 0; i < non_terminal.size(); i++){
            for(String t : non_terminal){
                getFollow(t);
            }
        }
    }

    public static boolean isNodeExist(String name){


        return false;
    }

    // 生成预测分析表
    public static String[][] creatTable(){
        String[][] preTable = new String[non_terminal.size()][terminal.size()];
        for(int i = 0; i <non_terminal.size(); i++){
            // 遍历每个非终止符右侧的产生式
            HashMap<String, String> firstset = first.get(non_terminal.get(i));
            HashMap<String, String> followset = follow.get(non_terminal.get(i));
            for(int j = 0 ;j < terminal.size();j++){

                if(firstset != null){
                    if(firstset.containsKey(terminal.get(j))){
                        preTable[i][j] = firstset.get(terminal.get(j));
                    }
                }
            }
            if(followset!=null){
                for(String a : followset.keySet()){
                    if(preTable[i][terminal.indexOf(a)]==null){

                        if(non_terminal.get(i).equals(followset.get(a))){
                            preTable[i][terminal.indexOf(a)] = "@";
                        }else{
                            preTable[i][terminal.indexOf(a)] = followset.get(a);
                        }
                    }
                }
            }
            if(firstset!=null&&firstset.containsKey("@")){
                for(String a : followset.keySet()){
                    if(preTable[i][terminal.indexOf(a)]==null){
                        if(non_terminal.get(i).equals(followset.get(a))){
                            preTable[i][terminal.indexOf(a)] = "@";
                        }else{
                            preTable[i][terminal.indexOf(a)] = followset.get(a);
                        }
                    }
                }
            }

        }
       // showTable(preTable);
        return preTable;
    }
    public static void showTable(String[][] preTable){

        // 打印表头
        System.out.print("    ");
        for (String t : terminal) {
            System.out.printf("   "+t);
        }
        System.out.println();

        // 打印分隔线
        for (int i = 0; i <= terminal.size() * 4 + 4; i++) {
            System.out.print("-");
        }
        System.out.println();

        // 打印表格内容
        for (int i = 0; i < non_terminal.size(); i++) {
            System.out.print(non_terminal.get(i) + " | ");
            for (int j = 0; j < terminal.size(); j++) {
                System.out.printf("     " + preTable[i][j]);
            }
            System.out.println();
        }
    }
    public static void inputTokens(List<String> tokens){
        for (int i = tokens.size() - 1; i >= 0; i--) {
                inputStack.push(tokens.get(i));
        }

        List<Integer> l = getTokensLine();

        for (int i = l.size() - 1; i >= 0; i--) {
            inputStackLine.push(l.get(i));
        }
        System.out.println("l = " + l);
    }

    public static LL1Error createTree(){

        // 用于存储终止符
        Stack<String> tokenStack = new Stack<>();
        String[][] preTable = creatTable();
        tokenStack.push("RETURN");
        tokenStack.push("Program");
        int length = 0;

        while(!inputStack.isEmpty()) {
            if(tokenStack.peek().replaceAll("`+$", "").equals(inputStack.peek())){
                tokenStack.pop();
                inputStack.pop();
                inputStackLine.pop();
            }else if(tokenStack.peek().replaceAll("`+$", "").equals("@")){
                tokenStack.pop();
            }
            else{
                int i = non_terminal.indexOf(tokenStack.peek().replaceAll("`+$", ""));
                int j = terminal.indexOf(inputStack.peek());
                if( i != -1 &&preTable[i][j] != null){
                    String temp = tokenStack.pop();
                    Node node = nodes.getNodeByName(temp);

                    String[] tokens = preTable[i][j].split(" ");
                    int leafSize = tokens.length;
                    for(int k = tokens.length -1 ;k >=0 ;k--){
                        String t ;
                        if(nowNodes.contains(tokens[k])){
                            StringBuilder sb = new StringBuilder(tokens[k]);
                            sb.append("`");
                            t = sb.toString();
                        }else{
                            t = tokens[k];
                        }
                        nowNodes.add(t);
                        Node child = new Node(t);
                        tokenStack.push(t);
                        node.addChild(child);
                    }

                }
                else{

                    String error = "An error found in line " + inputStackLine.pop() + " need " + tokenStack.pop() + ".";
                    return new LL1Error(error);
                }
            }

        }
        return null;
    }
    // 树形结果显示
    public static void graph(){
        nodes.printBeautifulTree("|--");
    }

    // 文字结果显示
    public static void text(){
        nodes.printTree(0);
    }

    public static void storeGraph(String path){
        nodes.printGraphTree(path);

    }
    public static void storeTxt(String path){
        nodes.printTxtTree(nodes,0,path);

    }
    public static void printError(){
        if(error != null){
            error.printError();
        }
    }
    public static LL1Error doLL1(){
        showFirst();
        showfollow();
        creatTable();
        inputTokens(getTokens());
        error = createTree();
        return error;
    }




}
