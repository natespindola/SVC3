package app.controller;


import app.model.dao.UserDao;
import core.Transacao;

public class UserController
{
    
    public boolean authenticateUser(String user, String password) throws Exception
    {
            boolean result;
            Transacao tr = new Transacao();
            UserDao dao = new UserDao();
            try {
                tr.beginReadOnly();
                if ((dao.isUserRegistered(user, password, tr))) {
                    result = true;
                }else{
                    result = false;
                }
            } catch (Exception e) {
                tr.rollback();
                e.printStackTrace();
                result = false;
            }
                return result;
    }
    
    public String getUserType(String nome) throws Exception
    {
        Transacao tr = new Transacao();
        try {
            UserDao dao = new UserDao();
            tr.beginReadOnly();
            String userType = dao.userType(tr, nome);
            tr.commit();
            return userType;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do usuário");
            e.printStackTrace();
            return null;
        }
    }
    /*
    public Vector remover (User user) throws Exception{

        Vector<String> businessException = new Vector();
        // efetuando a transacao
        Transacao tr = new Transacao();
        try {
                int retorno_funcao;
                tr.begin();
                UserDao dao = new UserDao();
                retorno_funcao = dao.delete(user,tr);
                tr.commit();
                Log log = new Log();
                if(retorno_funcao!=0){
                    log.setMessage("Usuário removido com sucesso.");
                }else{
                    businessException.add("nao existe usuario");
                    log.setMessage("Usuário inexistente.");
                }
                log.setRelationshipId(getLastInsert());
                log.setLogType(LogTypeEnum.USUARIO.name());
                LogController.remover(log);
                return businessException;
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("remover");
                error.setController("User");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao remover " + user.getName());
                ErrorLogController.incluir(error);
                businessException.add("servidor");
            }
            return businessException;
    } // remover
        
    public Vector incluir (User user,String password, boolean hasPassword) throws Exception{

                Vector<String> businessException = new Vector();
                // validacao das regras de negocio
                if (!isUserValid(user, password, businessException, hasPassword, true)) {
                    return businessException;
                }
            // efetuando a transacao
        Transacao tr = new Transacao();

        try {
                tr.begin();
                UserDao dao = new UserDao();
                dao.save(user,tr);
                tr.commit();
                Log log = new Log();
                log.setMessage("Usuário cadastrado com sucesso.");
                log.setRelationshipId(getLastInsert());
                log.setLogType(LogTypeEnum.USUARIO.name());
                LogController.incluir(log);
                return businessException;
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("incluir");
                error.setController("User");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao incluir " + user.getName());
                ErrorLogController.incluir(error);
                businessException.add("servidor");
            }
            return businessException;
        } // incluir

        private boolean isUserValid(User user, String password, Vector error, boolean hasPassword, boolean isNew) throws Exception
        {
            boolean result = true;
            if(hasPassword) {
                if (!password.equals(user.getPassword())) {
                    error.add("#div_confirm_password");
                    result = false;
                }
                if (!Utils.matchExpression("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$",user.getPassword())) {
                    error.add("#div_password");
                    result = false;
                }
            }
            if (isEmpty(user.getName()) || user.getName().length() > 200) {
                error.add("#div_nome");
                result = false;
            }
            if(isEmpty(user.getDocument())){
                error.add("#div_documento");
                result = false;
            }
            if(isEmpty(user.getEmail()) || !(Utils.isValidEmailAddress(user.getEmail()))){
                error.add("#div_email");
                result = false;
            }
            if(isEmpty(user.getProfile())){
                error.add("#div_perfil");
                result = false;
            }
            if(user.getProfile().equals(ProfileEnum.FISICA.name())){
                if(user.getName().indexOf(" ") <= 0){
                    error.add("#div_nome");
                    result = false;
                }
                if(!Utils.isValidCPF(user.getDocument())){
                    error.add("#div_documento");
                    result = false;
            }}else {
                if (!Utils.isValidCNPJ(user.getDocument())) {
                    error.add("#div_documento");
                    result = false;
                }
            }
            if(isNew) {
                Transacao tr = new Transacao();
                UserDao dao = new UserDao();
                try {
                    tr.beginReadOnly();
                    if ((dao.isUserRegistered(user, tr))) {
                        result = false;
                        error.add("usuario");
                    }
                    tr.commit();
                } catch (Exception e) {
                    tr.rollback();
                    e.printStackTrace();
                    result = false;
                }
            }
                return result;
        }
       
        public Vector atualizar(long id, User user, String password) throws Exception
        {
            Vector<String> businessException = new Vector();
            // validacao das regras de negocio
            boolean hasPassword = true;
            if(user.getPassword().isEmpty() && password.isEmpty()){
                hasPassword = false;
            }
            if (!isUserValid(user, password, businessException, hasPassword, false)) {
                return businessException;
            }
            // efetuando a transacao
            Transacao tr = new Transacao();

            try {
                tr.begin();
                UserDao dao = new UserDao();
                dao.update(user, id, tr);
                if(hasPassword) {
                    dao.mandarsenha(tr, user.getEmail(), user.getPassword());
                }
                tr.commit();
                this.insertLog("Usuário atualizado com sucesso.",id);
                return businessException;
            } catch (Exception e) {
                tr.rollback();
                this.errorException(e, "atualizar");
                businessException.add("servidor");
            }
            return businessException;
        }

        public User buscar(int idobj) throws Exception{
//            Transacao tr = new Transacao();
//            try{
//                tr.beginReadOnly();
//                ContatoData cdata = new ContatoData();
//                ContatoDO c = cdata.buscar(idobj, tr);
//                tr.commit();
//                return c;
//            } catch (Exception e) {
//                tr.rollback();
//                System.out.println("erro ao buscar" + idobj);
//                e.printStackTrace();
//            }
            return null;
        } // buscar

        public Vector pesquisar(String nome) {
//            if ( isEmpty(nome) )
//                return null;
//
//            Transacao tr = new Transacao();
//            try {
//                tr.beginReadOnly();
//                ContatoData cdata = new ContatoData();
//                Vector v = cdata.pesquisarPorNome(nome, tr);
//                tr.commit();
//                return v;
//            } catch(Exception e) {
//                System.out.println("erro ao pesquisar " + nome);
//                e.printStackTrace();
//            }
            return null;
        } // pesquisar

        private boolean isEmpty(String s) {
            if (null == s)
                return true;
            if (s.length() == 0)
                return true;
            return false;
        }
        
        public ArrayList<User> EncontrarTodos() throws Exception {
            Transacao tr = new Transacao();
            try{
                UserDao userdao = new UserDao(); 
                tr.beginReadOnly();
                ArrayList<User> UserArray = userdao.findAll(tr);
                tr.commit();
                return UserArray;
            }catch(Exception e) {
                System.out.println("erro ao gerar lista de usuarios");
                e.printStackTrace();
                return null;
            }
        }
        
        public String[] myproducts() throws Exception
        {
            Transacao tr = new Transacao();

            try{
                tr.beginReadOnly();
                UserDao dao = new UserDao();
                String[] u = dao.myproducts(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                //String erro[] =new String[10000];
                //erro[0] = "Errrrrou!";
                //return erro;
            }
            return null;
        }
        
        public String esquecisenha(String verifymail) throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                UserDao dao = new UserDao();
                String email = dao.esquecisenha(tr,verifymail);
                tr.commit();
                return email;
            } catch (Exception e) {
                tr.rollback();
                String erro;
                erro = "O e-mail digitado não consta em nossa base de dados. Favor inserir um e-mail válido.";
                return erro;
            }

        }

        public String mandarsenha(String verifyemail) throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.begin();
                UserDao dao = new UserDao();
                String senha = Utils.randomString();
                dao.mandarsenha(tr, verifyemail, senha);
                tr.commit();
                User user = this.getUserFromEmail(verifyemail);
                this.insertLog("Senha Enviada com sucesso", user.getId());
                return senha;
            } catch (Exception e) {
                tr.rollback();
                return null;
            }

        }

        private long getLastInsert() throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                UserDao dao = new UserDao();
                long u = dao.findLastInsertId(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                System.out.println("erro ao buscar");
                e.printStackTrace();
            }
            return -1;
        }

//        public static void main(String [] args) {
//            // Recipient's email ID needs to be mentioned.
//
//        }
        
         public String userName(String id) throws Exception
    {
        Transacao tr = new Transacao();
        try {
            UserDao dao = new UserDao();
            tr.beginReadOnly();
            String titulo = dao.userNameFromId(tr, id);
            tr.commit();
            return titulo;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do usuário");
            e.printStackTrace();
            return null;
        }
    }
        
    public User getUserFromEmail(String email) throws Exception
    {   
        User user = new User();
        Transacao tr = new Transacao();
        UserDao dao = new UserDao();
        try{
            tr.beginReadOnly();
            user = dao.getUserFromEmail(tr, email);
            tr.commit();
            return user;
            }catch(Exception e){
            tr.rollback();
            this.errorException(e,"erro ao encontrar usuario a partir de email");
            return null;
        }
    }

    public void sentActivationEmail(String email) throws Exception
    {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            UserDao userDao = new UserDao();
            User user = userDao.getUserFromEmail(tr,email);
            tr.commit();
            String encodeId = Utils.encodeMD5(String.valueOf(user.getId())+user.getEmail());
            String link = "http://localhost:8080/ativacao.jsp?user="+encodeId+"&userId="+user.getId()+"";
            String subject = "Ative sua Conta No Sebo Virtual";
            String message = "<h3>Olá Senhor/Senhora "+ user.getName() +"</h3></br></br></br> <p>Ative sua " +
                "conta clicando no link abaixo e tenha acesso a tudo que podemos agora, e vamos oferecer</p></br></br>" +
                "<a href="+link+">Clique Aqui</a>";

            SendEmail se = new SendEmail();
            se.sendMail(subject,message,user.getEmail());
            this.insertLog("Email de ativação enviado com sucesso.", user.getId());
        }catch (Exception e){
            tr.rollback();
            this.errorException(e,"sentActivationEmail");
        }
    }

    public boolean activateUser(long id, String hash) throws Exception
    {
        Transacao tr = new Transacao();
        try{
            tr.beginReadOnly();
            UserDao userDao = new UserDao();
            User user = userDao.findById(tr, id);
            tr.commit();
            String prove = Utils.encodeMD5(user.getId()+user.getEmail());
            if(prove.equals(hash)){
                tr.begin();
                userDao.activateUser(tr, user.getId());
                tr.commit();
                this.insertLog("Usuário ativado com sucesso.",user.getId());
                return true;
            }else{
                this.insertLog("Usuário Não foi ativado, possível fraude", user.getId());
                return false;
            }
        }catch (Exception e){
            tr.rollback();
            this.errorException(e,"activateUser");
        }
        return false;
    }

    private void errorException(Exception e, String action)
    {
        try{
        ErrorLog error = new ErrorLog();
        error.setAction(action);
        error.setController("User");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        error.setErrorTrace(sw.toString());
        ErrorLogController.incluir(error);
    }catch (Exception finale){
            //Se der essa exceção aí fudeu
        finale.printStackTrace();
        }
    }
    
    
        public ArrayList<User> buscarBloqueados() throws Exception {
            Transacao tr = new Transacao();
            try{
                UserDao userdao = new UserDao(); 
                ArrayList<User> UserArray;
                tr.beginReadOnly();
                UserArray = userdao.buscarBloqueados(tr);
                tr.commit();
                return UserArray;
            }catch(Exception e) {
                tr.rollback();
                this.errorException(e,"erro ao Buscar usuario");
                return null;
            }
        }

        
    public boolean desbloquearUsuario(Long id) throws Exception
    {
        Transacao tr = new Transacao();
        try{
            tr.beginReadOnly();
            UserDao usuario = new UserDao();
            User user = usuario.findById(tr, id);
            tr.commit();
            tr.begin();
            usuario.desbloquearUsuario(tr, user.getId());
            tr.commit();
            this.insertLog("Usuário desbloqueado com sucesso.", user.getId());
            return true;

        }catch (Exception e){
            tr.rollback();
            this.errorException(e,"erro ao desbloquear usuario");
        }
        return false;
    }

    private void insertLog(String message, long id) throws Exception
    {
        Log log = new Log();
        log.setMessage(message);
        log.setRelationshipId(id);
        log.setLogType(LogTypeEnum.USUARIO.name());
        LogController.incluir(log);
    }*/
}
