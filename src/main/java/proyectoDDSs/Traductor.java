package proyectoDDSs;

public interface Traductor <T>
{
	//Tengo tipo de dato generico xq debo variar segun la traduccion que se implemente
    public abstract T traduccion(String accion);
    //De momento solo traduzco la acción, habria que ver en las siguientes entregas
    //si me piden un formato de traducción mas complejo o que
}
