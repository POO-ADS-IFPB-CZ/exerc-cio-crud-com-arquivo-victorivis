import Dao.PessoaDao;
import Models.Pessoa;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static boolean menu(){
        PessoaDao rw = new PessoaDao();
        Scanner input = new Scanner(System.in);

        String nome;
        String email;

        System.out.println("/********************************************/");
        System.out.println("/*   Digite 1 para ver a lista de pessoas   */");
        System.out.println("/*   Digite 2 para adicionar uma pessoa     */");
        System.out.println("/*   Digite 3 para excluir por email        */");
        System.out.println("/*   Digite 4 para excluir por nome         */");
        System.out.println("/********************************************/");

        switch (input.nextInt()){
            case 1:
                rw.ler();
                break;
            case 2:
                System.out.printf("Digite o nome: ");
                nome = input.next();

                System.out.printf("Digite o email: ");
                email = input.next();

                rw.escrever(new Pessoa(nome, email));
                break;
            case 3:
                System.out.printf("Digite o email: ");
                email = input.next();

                if(rw.apagarPorEmail(email)){
                    System.out.println("Pessoa deletada com sucesso");
                }
                else{
                    System.out.println("Erro. Email não existe");
                }
                break;
            case 4:
                System.out.printf("Digite o nome: ");
                nome = input.next();

                if(rw.apagarPorEmail(nome)){
                    System.out.println("Pessoa deletada com sucesso");
                }
                else{
                    System.out.println("Erro. Nome não existe");
                }
                break;
        }



        return true;
    }


    public static void main(String[] args) {
        /*
        Pessoa p = new Pessoa("Pedro", "Pedoka@gmail.com")1;

        PessoaDao rw = new PessoaDao();
        System.out.println(rw.ler());
        */

        menu();
    }
}