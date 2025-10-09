# 🏥 Trabalho Prático – Sistema de Gerenciamento Hospitalar  

### 🎯 Objetivo  
Implementar um *Sistema de Gerenciamento Hospitalar* em *Java, aplicando conceitos avançados de **Programação Orientada a Objetos (POO), com foco em **herança, polimorfismo, encapsulamento, persistência de dados* e *regras de negócio mais complexas*.  

---
## Descrição do Projeto

Desenvolvimento de um sistema de gerenciamento hospitalar utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

## Dados do Aluno

- **Nome completo:** João Pedro da Nóbrega Souza Cordeiro
- **Matrícula:** 251023184
- **Curso:** Engenharias - Orientação a Objetos 2025.2
- **Turma:** 2

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   javac -d out\production\hospital-manager src\hospitalmanager\app\App.java
   Basta colar esse comando em um terminal aberto na pasta hospital-manager (padrão do proejeto)

2. **Execução:**  
   java -cp out\production\hospital-manager hospitalmanager.app.App
   Esse comando também deve ser colado no mesmo lugar, mas só após a compilação

### Estrutura de Pastas

    hospitalmanager/                 # pasta principal do projeto
    │
    ├── imagens/                     # pasta com as imagens de execução
    │
    ├── app/                         # pasta da aplicação, contém a main executável
    │
    ├── dominio/                     # principais entidades da aplicação
    │
    ├── interfaces/                  # interfaces gráficas
    │   ├── elementos/               # elementos gráficos comuns a muitos menus
    │   └── modelosTabela/           # modelos de tabela customizados para cada menu
    │
    ├── persistencia/                # entidades responsáveis pela persistência de dados
    │
    └── dados/                       # onde os dados são armazenados e lidos
        └── relatorios/              # onde as exportações de relatórios são salvas
            ├── consultas/
            └── internacoes/

3. **Versão do JAVA utilizada:**  
   `jdk Java 24 `
---

## Vídeo de Demonstração

- [Vídeo](https://youtu.be/aMjeJDWlBW4)

## Prints da Execução

1. [Menu Inicial](imagens/MenuInicial.png)
2. [Menu Pacientes](imagens/Pacientes.png)
3. [Pacientes Especiais (ao clicar em Alternar)](imagens/Pacientes%20Especiais%20(ao%20clicar%20em%20Alternar).png)
4. [Cadastro Paciente](imagens/Cadastro%20Paciente.png)
5. [Menu de opções (botão ver da tabela)](imagens/Menu%20de%20opções%20(botão%20ver%20da%20tabela).png)
6. [Botão Consultas do Paciente](imagens/Botão%20Consultas%20do%20Paciente.png)
7. [Botão internações do Paciente](imagens/Botão%20internações%20do%20Paciente%20(outro%20paciente).png)
8. [Menu Médicos](imagens/Menu%20Médicos.png)
9. [Menu Opções de Adicionar](imagens/Menu%20Adicionar%20Medicos.png)
10. [Menu Adicionar Médico](imagens/Menu%20Adicionar%20Médico.png)
11. [Menu AdicionarEspecialidade](imagens/Menu%20adicionar%20Especialidade.png)
12. [Menu da Agenda (botão ver na tabela)](imagens/Menu%20da%20Agenda%20(botão%20ver%20na%20tabela).png)
13. [Menu Consultas](imagens/Menu%20Consultas.png)
14. [Adicionar Consulta](imagens/Adicionar%20Consulta.png)
15. [Botão Ver da tabela](imagens/Botão%20Ver%20da%20tabela.png)
16. [Menu Mostrar relatório](imagens/Menu%20Mostrar%20Relatório.png)
17. [Menu concluir consulta](imagens/Menu%20concluir%20consulta.png)
18. [Menu Internações](imagens/Menu%20Internações.png)
19. [Adicionar Internação](imagens/Adicionar%20Internação.png)
20. [Botão Ver da Tabela](imagens/Botão%20Ver%20da%20TabelaOutra.png)
21. [Botão mostrar relatório](imagens/Botão%20mostrar%20relatório.png)
22. [Menu Plano de Saúde](imagens/Menu%20Plano%20de%20Saúde.png)
23. [Adicionar Plano](imagens/Adicionar%20Plano.png)
24. [Menu Geral (Testes)](imagens/Menu%20Geral%20(de%20testes).png)
---

---

## Observações (Extras ou Dificuldades)

- Trabalhar com a biblioteca Swing foi, definitivamente, a parte mais complicada do trabalho.
- Embora seja uma biblioteca com muitas classes e métodos úteis, entender o que eu precisava usar
- foi e como usar foi um desafio enorme. Eu achei que a implementação de uma interface gráfica 
- desse tipo fosse dificultar um pouquinho o trabalho **como fui tolo!** algo que seria um simples
- System.out.println() se tornou facilmente 1 hora de trabalho. A estrutura de menus funciona como
- um conjunto, para terminar uma função, precisava terminar outras 5.
- Ao menos aprendi como fazer interfaces gráficas customizadas!

---

## Contato

- **Email:** nbg.cordeiro@gmail.com

---

### 🖥️ Descrição do Sistema  

O sistema deve simular o funcionamento de um hospital com cadastro de *pacientes, médicos, especialidades, consultas e internações*.  

1. *Cadastro de Pacientes*  
   - Pacientes comuns e pacientes especiais (ex: com plano de saúde).  
   - Cada paciente deve ter: nome, CPF, idade, histórico de consultas e internações.  

2. *Cadastro de Médicos*  
   - Médicos podem ter especialidades (ex: cardiologia, pediatria, ortopedia).  
   - Cada médico deve ter: nome, CRM, especialidade, custo da consulta e agenda de horários.  

3. *Agendamento de Consultas*  
   - Um paciente pode agendar uma consulta com um médico disponível.  
   - Consultas devem registrar: paciente, médico, data/hora, local, status (agendada, concluída, cancelada).  
   - Pacientes especiais (plano de saúde) podem ter *vantagens*, como desconto.  
   - Duas consultas não podem estar agendadas com o mesmo médico na mesma hora, ou no mesmo local e hora

4. *Consultas e Diagnósticos*  
   - Ao concluir uma consulta, o médico pode registrar *diagnóstico* e/ou *prescrição de medicamentos*.  
   - Cada consulta deve ser registrada no *histórico do paciente*.  

5. *Internações*  
   - Pacientes podem ser internados.  
   - Registrar: paciente, médico responsável, data de entrada, data de saída (se já liberado), quarto e custo da internação.  
   - Deve existir controle de *ocupação dos quartos* (não permitir duas internações no mesmo quarto simultaneamente).  
   - Internações devem poder ser canceladas, quando isso ocorrer, o sistema deve ser atualizado automaticamente.

6. *Planos de saúde*    
   -  Planos de saude podem ser cadastrados.
   -  Cada plano pode oferecer *descontos* para *especializações* diferentes, com possibilidade de descontos variados.
   -  Um paciente que tenha o plano de saúde deve ter o desconto aplicado.
   -  Deve existir a possibilidade de um plano *especial* que torna internação de menos de uma semana de duração gratuita.
   -  Pacientes com 60+ anos de idade devem ter descontos diferentes.

7. *Relatórios*  
   - Pacientes cadastrados (com histórico de consultas e internações).  
   - Médicos cadastrados (com agenda e número de consultas realizadas).  
   - Consultas futuras e passadas (com filtros por paciente, médico ou especialidade).  
   - Pacientes internados no momento (com tempo de internação).  
   - Estatísticas gerais (ex: médico que mais atendeu, especialidade mais procurada).  
   - Quantidade de pessoas em um determinado plano de saúde e quanto aquele plano *economizou* das pessoas que o usam.

---

### ⚙️ Requisitos Técnicos  
- O sistema deve ser implementado em *Java*.  
- Interface via *terminal (linha de comando)*.  
- Os dados devem ser persistidos em *arquivos* (.txt ou .csv).  
- Deve existir *menu interativo*, permitindo navegar entre as opções principais.  

---

### 📊 Critérios de Avaliação  

1. *Modos da Aplicação (1,5)* → Cadastro de pacientes, médicos, planos de saúde, consultas e internações.  
2. *Armazenamento em arquivo (1,0)* → Dados persistidos corretamente, leitura e escrita funcional.  
3. *Herança (1,0)* → Ex.: Paciente e PacienteEspecial, Consulta e ConsultaEspecial, Médico e subclasses por especialidade.  
4. *Polimorfismo (1,0)* → Ex.: regras diferentes para agendamento, preços de consultas.
5. *Encapsulamento (1,0)* → Atributos privados, getters e setters adequados.  
6. *Modelagem (1,0)* → Estrutura de classes clara, bem planejada e com relacionamentos consistentes.  
7. *Execução (0,5)* → Sistema compila, roda sem erros e possui menus funcionais.  
8. *Qualidade do Código (1,0)* → Código limpo, organizado, nomes adequados e boas práticas.  
9. *Repositório (1,0)* → Uso adequado de versionamento, commits frequentes com mensagens claras.  
10. *README (1,0)* → Vídeo curto (máx. 5 min) demonstrando as funcionalidades + prints de execução + explicação da modelagem.  

🔹 *Total = 10 pontos*  
🔹 *Pontuação extra (até 1,5)* → Melhorias relevantes, como:  
- Sistema de triagem automática com fila de prioridade.  
- Estatísticas avançadas (tempo médio de internação, taxa de ocupação por especialidade).  
- Exportação de relatórios em formato .csv ou .pdf.  
- Implementação de testes unitários para classes principais.  
- Menu visual.
