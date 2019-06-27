import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Toolkit;

public class EditorTxt extends JFrame{
	
	// A JTextArea é uma área para exibir textos simples
	protected JTextArea texto = new JTextArea();
	// Fornece uma interface gráfica para navegar no sistema de arquivos
	protected JFileChooser localArquivo = new JFileChooser();
	
	public EditorTxt() {
		
		//altera icone 
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Cliente\\Desktop\\trabalho editor\\iconeTXT.jpg"));
		
		// A JScrollPane gerencia uma janela, barras de rolagem verticais e horizontais opcionais
		JScrollPane scrollPane = new JScrollPane (texto);
		
		//define o titulo da janela
		setTitle("Editor de Texto");
		
		//adiciona a area do texto
		getContentPane().add(scrollPane);
		
		// cria a barra de menu
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		//cria o menu
		JMenu m1 = new JMenu("Menu");
		
		//adiciona o menu a barra de menus
		barraMenu.add(m1);
		
		//adiciona as opçoes do menu
		m1.add(Abrir);
		m1.add(Salvar);
		
		//cria um separador no menu
		m1.addSeparator();
		m1.add(Sair);
		
		// finaliza o programa ao fechar a janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//define tamanho da janela ao abrir programa
		setSize(700,600);
		
		// faz o programa abrir no meio da tela 
		setLocationRelativeTo(null);
		
		// faz a janela do programa ficar visivel
		setVisible(true);
	}
	
	
	//açoes das opçoes do menu
		AbstractAction Abrir = new AbstractAction("Abrir Arquivo") { //fornece funçoes definidas atraves do actionPerformed para o abrir do menu

			public void actionPerformed(ActionEvent e) { // açao do comando abrir
				//Abre uma caixa de diálogo do seletor de arquivos "Abrir Arquivo". 
				if(localArquivo.showOpenDialog(getParent())== JFileChooser.APPROVE_OPTION) {
					AbrirArquivo(localArquivo.getSelectedFile().getAbsolutePath()); //executa o metodo abrir arquivo passando os parametros do arquivo selecionado 
				}
			}	
		};
		
		AbstractAction Salvar = new AbstractAction("Salvar Arquivo") {//fornece funçoes definidas atraves do actionPerformed para o salvar do menu
			public void actionPerformed(ActionEvent e) {
				SalvarArquivo();//executa o metodo salvar arquivo
				}
		};
	
		//fecha o programa 
		AbstractAction Sair = new AbstractAction("Sair") {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}	
		};
		
		// metodos, é equivalente a uma função, subrotina ou procedimento 
		
		public void AbrirArquivo(String nomeArquivo) {
			try {
				FileReader lerArquivo = new FileReader(nomeArquivo);// cria uma variavel de leitura para arquivos de caracteres, e ja recebe o arquivo selecionado.
				texto.read(lerArquivo, null);// escreve o texto na tela, que esta no arquivo selecionado 
				lerArquivo.close();//Fecha o fluxo do FileReader libera quaisquer recursos do sistema associados a ele. 
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo"); //retorna erro caso ocorra algum problema com os comandos dentro do bloco
			}
		}
		
		public void SalvarArquivo() {
			 //Abre uma caixa de diálogo do seletor de arquivos "Salvar arquivo"
			if(localArquivo.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					FileWriter escreverArquivo = new FileWriter(localArquivo.getSelectedFile().getAbsolutePath()+".txt");//cria uma variavel de escrita de arquivos, ja definada com o nome e o tipo do arquivo
					texto.write(escreverArquivo);//Armazena o conteúdo do texto que esta na tela na variavel escreverArquivo.
					escreverArquivo.close();//Fecha o fluxo do FileWriter e libera quaisquer recursos do sistema associados a ele.
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo"); //retorna erro caso ocorra algum problema com os comandos dentro do bloco				}
			}
		}
}
}