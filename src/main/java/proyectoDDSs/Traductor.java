package proyectoDDSs;

public interface Traductor <T>
{
	//Tengo tipo de dato generico xq debo variar segun la traduccion que se implemente
    public abstract T traduccion(String accion);
    //De momento solo traduzco la accion, habria que ver en las siguientes entregas
    //si me piden un formato de traduccion mas complejo o que
}
