# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

require 'clases/shell/shell_exec'
require 'clases/ui/ui_texto_plano'

con = ShellExec.new("/home/david/tmp")
ui = UITextoPlano.new
ui.consola= con
ui.iniciar
