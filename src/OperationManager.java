public class OperationManager {

    public static boolean IAmDownloading(int otherOperationCode)
    {
        try{
            OperationCodes opCode = OperationCodes.values()[otherOperationCode];

            switch(opCode){
                case Hochladen:
                case Herunterladen:
                case Ersetzen:
                case Umbennen:
                case Entfernen:
                case Ausschneiden:
                case Einfügen:
                case Kopieren:
                case Verschieben:
                    return false;
                case Eigenschaften:
                    return true;
            }
        }
        catch(Exception ex){
            return false;
        }

        return false;
    }

    public static boolean SomeoneGetsErased(int otherOperationCode)
    {
        try{
            OperationCodes opCode = OperationCodes.values()[otherOperationCode];

            switch(opCode){
                case Hochladen:
                case Herunterladen:
                case Ersetzen:
                case Umbennen:
                case Entfernen:
                case Ausschneiden:
                case Einfügen:
                case Kopieren:
                case Verschieben:
                    return false;
                case Eigenschaften:
                    return true;
            }
        }
        catch(Exception ex){
            return false;
        }

        return false;
    }

    public enum OperationCodes{
        Hochladen,
        Herunterladen,
        Kopieren,
        Ausschneiden,
        Einfügen,
        Verschieben,
        Umbennen,
        Entfernen,
        Eigenschaften,
        Ersetzen
    }
}
