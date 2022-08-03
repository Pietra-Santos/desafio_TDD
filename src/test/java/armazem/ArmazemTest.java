package armazem;

import exception.ExceptionIngredienteNaoEncontrado;
import ingredientes.Fruta;
import ingredientes.Ingrediente;
import ingredientes.TipoFruta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class ArmazemTest {

    public Armazem armazem;

    @BeforeEach
    public void beforeEach()
    {
        armazem = new Armazem();
        Ingrediente banana = new Fruta(TipoFruta.Banana);
        armazem.cadastrarIngredienteEmEstoque(banana);
    }

    @Test
    void ArmazemCadastrarIngredienteEmEstoqueTest()
    {

        Ingrediente abacate = new Fruta(TipoFruta.Abacate);
        armazem.cadastrarIngredienteEmEstoque(abacate);

        Assertions.assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(abacate));
    }

    @Test
    void ArmazemCadastrarIngredienteEmEstoqueIngredienteJaCadastradoTest()
    {
        Ingrediente banana = new Fruta(TipoFruta.Banana);

        var exception = assertThrows(Exception.class, () -> armazem.cadastrarIngredienteEmEstoque(banana), "Exception not found");

        Assertions.assertEquals("_Ingrediente já cadastrado_", exception.getMessage());
    }

    @Test
    void ArmazemDescadastrarIngredienteNoEstoqueTest()
    {
        Ingrediente morango = new Fruta(TipoFruta.Morango);

        try{
            armazem.descadastrarIngredienteEmEstoque(morango);
            fail("Não foi lançado a exceção esperada");

        } catch(ExceptionIngredienteNaoEncontrado exception){
            assertEquals("_Ingrediente não encontrado_", exception.getMessage());
        }
    }

    @Test
    void ArmazemAdicionarQuantidadeDoIngredienteEmEstoqueIngredienteNaoEncontradoTest()
    {
        Ingrediente fruta = new Fruta(TipoFruta.Abacate);

        try{
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(fruta, 1);
            fail("Não foi lançado a exceção esperada");

        } catch(IllegalArgumentException exception){
            assertEquals("_Ingrediente não encontrado_", exception.getMessage());
        }
    }
    @Test
    void ArmazemReduzirQuantidadeDoIngredienteEmEstoqueIngredienteNaoEncontradoTest()
    {
        Ingrediente fruta = new Fruta(TipoFruta.Abacate);

        try{
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 1);
            fail("Não foi lançado a exceção esperada");

        } catch(IllegalArgumentException exception){
            assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage());
        }
    }

    @Test
    void ArmazemReduzirQuantidadeDoIngredienteEmEstoqueQuantidadeInvalidaTest()
    {
        Ingrediente fruta = new Fruta(TipoFruta.Banana);

        try{
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(fruta, 0);
            fail("Não foi lançado a exceção esperada");

        } catch(IllegalArgumentException exception){
            assertEquals("_Ingrediente não encontrado ou quantidade inválida_", exception.getMessage());
        }
    }
    @Test
    void ArmazemReduzirQuantidadeDoIngredienteEmEstoqueAtualizarQuantidadeTest()
    {
        Ingrediente banana = new Fruta(TipoFruta.Banana);

        armazem.adicionarQuantidadeDoIngredienteEmEstoque(banana, 10);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(banana, 5);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(banana));

    }
    @Test
    void ArmazemConsultarQuantidadeDoIngredienteEmEstoqueTest()
    {
        Ingrediente banana = new Fruta(TipoFruta.Banana);

        armazem.adicionarQuantidadeDoIngredienteEmEstoque(banana, 10);

        assertEquals(10, armazem.consultarQuantidadeDoIngredienteEmEstoque(banana));

    }
    @Test
    void ArmazemConsultarQuantidadeDoIngredienteEmEstoqueIngredienteNaoEncontradoTest()
    {
        Ingrediente morango = new Fruta(TipoFruta.Morango);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(morango));

        assertEquals("_Ingrediente não encontrado_", exception.getMessage());

    }
}

