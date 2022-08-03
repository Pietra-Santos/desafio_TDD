package exception;

public class ExceptionIngredienteNaoEncontrado extends IllegalArgumentException{

    static final String mensagem = "_Ingrediente não encontrado_";
    public ExceptionIngredienteNaoEncontrado() {
        super(mensagem);
    }
}
