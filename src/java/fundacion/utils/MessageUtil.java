/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.utils;


public class MessageUtil {

    public static String ShowSuccessMessage(String message) {
        return ShowMessage(message, 1);
    }
    
    public static String ShowErrorMessage(String message) {
        return ShowMessage(message, 0);
    }
    
    public static String ShowExceptionMessage(Exception ex) {
        return ShowErrorMessage("Ocurrio un error: " + ex.getMessage());
    }
    
    private static String ShowMessage(String message, int messageType) {
        String title = "", msgType = "";
        
        switch (messageType) {
            case 0:
                title = "Error";
                msgType = "error";
                break;
            case 1:
                title = "Exito";
                msgType = "success";
                break;
        }
        
        String returnedMessage = "swal.fire({"
                    + "type: '" + msgType +"', "
                    + "title: '" + title + "', "
                    + "text: \"" + message + "\", "
                    + "footer: ''});";
        
        return returnedMessage;
    }

}
