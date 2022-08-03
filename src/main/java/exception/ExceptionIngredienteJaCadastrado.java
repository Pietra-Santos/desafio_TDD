package exception;

public class ExceptionIngredienteJaCadastrado extends IllegalArgumentException{

    static final String mensagem = "_Ingrediente já cadastrado_";
    public ExceptionIngredienteJaCadastrado() {
        super(mensagem);
    }
}
