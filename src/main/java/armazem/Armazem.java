package armazem;

import exception.ExceptionIngredienteNaoEncontrado;
import exception.ExceptionIngredienteJaCadastrado;
import ingredientes.Ingrediente;

import java.util.Map;
import java.util.TreeMap;

public class Armazem {
   private TreeMap<Ingrediente, Integer> estoque;
   public Map<Ingrediente, Integer> getEstoque(){

       return estoque;
   };

    public Armazem(){
        this.estoque = new TreeMap<>();
    }

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente)
    {
        if(estoque.containsKey(ingrediente)){

            throw new ExceptionIngredienteJaCadastrado();
        }

        int quantidadeIngrediente = 0;

        estoque.put(ingrediente,quantidadeIngrediente);

    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente)
    {
        if(!estoque.containsKey(ingrediente))
        {
            throw new ExceptionIngredienteNaoEncontrado();
        }

        estoque.remove(ingrediente);

    }
    //public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) adiciona uma determinada quantidade de um ingrediente específico no estoque.
    // Retornando IllegaLArgumentException com a seguinte mensagem de erro: _Ingrediente não encontrado ou quantidade inválida_ ,
    // caso o ingrediente não exista no estoque ou a quantidade informada seja menor ou igual a zero.

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade){

       int quantidadeIngrediente = 0;

       if(quantidade <= 0)
       {
           throw new  IllegalArgumentException("_Ingrediente não encontrado ou quantidade inválida_");
       }

       if(!estoque.containsKey(ingrediente))
       {
           throw new ExceptionIngredienteNaoEncontrado();
       }

       quantidadeIngrediente = quantidade + estoque.get(ingrediente);

       estoque.remove(ingrediente);
       estoque.put(ingrediente, quantidadeIngrediente);

    }
    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidadeReduzida){

        if(!estoque.containsKey(ingrediente) || quantidadeReduzida <= 0)
        {
            throw new IllegalArgumentException("_Ingrediente não encontrado ou quantidade inválida_");
        }

        int quantidadeAtual = estoque.get(ingrediente);
        int novaQuantidade = quantidadeAtual - quantidadeReduzida;

        estoque.remove(ingrediente);
        estoque.put(ingrediente, novaQuantidade);
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente)
    {
        if (!estoque.containsKey(ingrediente))
        {
            throw new IllegalArgumentException("_Ingrediente não encontrado_");
        }
        return estoque.get(ingrediente);
    }

}
