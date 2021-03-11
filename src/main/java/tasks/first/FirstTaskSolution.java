package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class FirstTaskSolution implements FirstTask {

    public ArrayList<Vertex> transformBooleanArray(boolean[] boolArray, ArrayList<Vertex> vertexes) {
        ArrayList<Vertex> returnedList = new ArrayList<>();
        for (int i = 0; i < boolArray.length; i++) {
            if (boolArray[i]) {
                returnedList.add(vertexes.get(i));
            }
        }
        return returnedList;
    }

    public boolean[][] makeBooleanTwoDimensionArray(int[][] integerArray) {
        boolean[][] resultArray = new boolean[integerArray.length][integerArray[0].length];
        for (int i = 0; i < integerArray.length; i++) {
            for (int j = 0; j < integerArray[i].length; j++) {
                if (integerArray[i][j] == 0) {
                    resultArray[i][j] = false;
                } else {
                    resultArray[i][j] = true;
                }
            }
        }
        return resultArray;
    }

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayList<Vertex> vertexes = new ArrayList<>();
        ArrayDeque<Vertex> vertexesDeque = new ArrayDeque<>();
        ArrayList<Vertex> resultList = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            vertexes.add(new Vertex(i));
        }
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            vertexes.get(i).setInheritedVertexes(transformBooleanArray(adjacencyMatrix[i], vertexes));
        }
        Vertex startVertex = vertexes.get(startIndex);
        vertexesDeque.offerFirst(startVertex);
        resultList.add(startVertex);

        for (int i = 0; i < startVertex.getInheritedVertexes().size(); i++) {
            vertexesDeque.offerLast(startVertex.getInheritedVertexes().get(i));
            resultList.add(startVertex.getInheritedVertexes().get(i));
        }
        while (resultList.size() != vertexes.size()) {
            for (int i = 0; i < vertexesDeque.getFirst().getInheritedVertexes().size(); i++) {
                if (!resultList.contains(vertexesDeque.getFirst().getInheritedVertexes().get(i))) {
                    vertexesDeque.offerLast(vertexesDeque.getFirst().getInheritedVertexes().get(i));
                    resultList.add(vertexesDeque.getFirst().getInheritedVertexes().get(i));
                }
            }
            vertexesDeque.pollFirst();

        }
        String result = "";
        for (int i = 0; i < resultList.size(); i++) {
            result += resultList.get(i).getIndex();
            if (i + 1 == resultList.size()) {
                result += ".";
            } else {
                result += ", ";
            }
        }
        return result;
    }

    @Override
    public Boolean validateBrackets(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        String openBrackets = "([{";
        String closedBrackets = ")]}";
        for (int i = 0; i < s.length(); i++) {
            boolean isClosedBrackets = closedBrackets.indexOf(s.charAt(i)) != -1;

            if(stack.size() == 0 && isClosedBrackets){
                return false;
            }

            if(openBrackets.indexOf(s.charAt(i)) != -1){
                stack.push(s.charAt(i));
            } else if(isClosedBrackets && stack.peek() != null){
                if(s.charAt(i) == ')' && stack.peek() == '('){
                    stack.pop();
                } else if(s.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if(s.charAt(i) == '}' && stack.peek() == '{'){
                    stack.pop();
                }
            }
        }
        return stack.size() <= 0;

    }

    @Override
    public Long polishCalculation(String s) {
        String operations = "+-*/";
        ArrayDeque<Long> numberStack = new ArrayDeque<>();
        String [] expInChars = s.split(" ");
        ArrayDeque<String> currentOperationsDeque = new ArrayDeque<>();

        for (String expInChar : expInChars) {
            if (operations.contains(expInChar)) {
                currentOperationsDeque.offerLast(expInChar);
            } else {
                numberStack.push(Long.parseLong(expInChar));
            }
        }

        while(numberStack.size() != 1){
            long a = numberStack.pop();
            long b = numberStack.pop();
            switch(currentOperationsDeque.getFirst()){
                case "+":
                    numberStack.push(a+b);
                    currentOperationsDeque.pollFirst();
                    break;
                case "-":
                    numberStack.push(a-b);
                    currentOperationsDeque.pollFirst();
                    break;
                case "*":
                    numberStack.push(a*b);
                    currentOperationsDeque.pollFirst();
                    break;
                case "/":
                    numberStack.push(a/b);
                    currentOperationsDeque.pollFirst();
            }
        }
        return numberStack.pop();
    }
}
