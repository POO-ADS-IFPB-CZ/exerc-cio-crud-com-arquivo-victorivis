package Dao;

import Models.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    final static String nomeArquivo = "salvar.ser";
    private File arquivo;

    public PessoaDao(){
        arquivo = new File(nomeArquivo);

        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<Pessoa> getPessoas(){
        if(arquivo.length()>0){
            //Há dados no arquivo, ler conjunto
            try{
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> supermercados =
                        (Set<Pessoa>) objectInputStream.readObject();
                return supermercados;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo");
            } catch (ClassNotFoundException e) {
                System.out.println("Falha ao ler arquivo");
            }
        }
        //Não há nada no arquivo, criar um novo conjunto
        return new HashSet<>();
    }

    public boolean escrever(Pessoa supermercado) {
        Set<Pessoa> supermercados = getPessoas();
        if(supermercados.add(supermercado)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(supermercados);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public boolean salvar(Set<Pessoa> supermercados) {
        try{
            FileOutputStream outputStream = new FileOutputStream(arquivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(supermercados);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Falha ao escrever no arquivo");
        }
        return false;
    }

    public boolean apagarPorEmail(String email){
        Set<Pessoa> listaPessoas = getPessoas();
        for(Pessoa p : listaPessoas){
            if(email.equals(p.getEmail())){
                listaPessoas.remove(p);
                salvar(listaPessoas);
                return true;
            }
        }
        return false;
    }

    public boolean apagarPorNome(String nome){
        Set<Pessoa> listaPessoas = getPessoas();
        for(Pessoa p : listaPessoas){
            if(nome.equals(p.getNome())){
                listaPessoas.remove(p);
                salvar(listaPessoas);
                return true;
            }
        }
        return false;
    }

    public void ler() {
        Set<Pessoa> listaPessoas = getPessoas();

        if(listaPessoas.isEmpty() == false){
            System.out.println("---------------------------------------------");
            for (Pessoa p : listaPessoas) {
                System.out.println("Nome: " + p.getNome());
                System.out.println("Email: " + p.getEmail());
                System.out.println("---------------------------------------------");
            }
        }
        else{
            System.out.println("Nenhuma pessoa foi adicionada até agora");
        }
    }
}
