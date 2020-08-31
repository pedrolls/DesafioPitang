package seleniumTest;

import Util.ConstantesSistema;

public class FindChomeDriver {

	//Aqui pego o diretorio do projeto
		String path = System.getProperty("user.dir");
		//Aqui pelo o serparador que uma "/"
		String folderSeparator = System.getProperty("file.separator");

		//Aqui é o caminho onde o driver se encontra dentro do projeto
		String relativePath = 
				folderSeparator + "WebContent" + 
				folderSeparator + "resources" + 
				folderSeparator + "lib";
		/**
		 * Metodo que retorna a String com caminho do arquivo do driver do google Chrome
		 * @return String
		 */
		public String getChormeDriverLocation() {
			return path  + relativePath + folderSeparator+ ConstantesSistema.CHROME_DRIVER;
		}
}
