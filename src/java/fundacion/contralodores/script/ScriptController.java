/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.contralodores.script;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;


@Named(value = "scriptController")
@SessionScoped
public class ScriptController implements Serializable {

    private String script;
    /**
     * Creates a new instance of ScriptController
     */
    public ScriptController() {
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
    
    
    public String mostrarScript() {
        String nuevoScript = String.copyValueOf(script.toCharArray());
        script = null;
        /*System.out.println(nuevoScript);
        System.out.println(script);*/
        return nuevoScript;
    }
}
