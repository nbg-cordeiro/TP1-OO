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

3. **Estrutura de Pastas:**  
   -hospitalmanager (pasta principal do projeto)
      --app (pasta da aplicação, contém a main executável)
      --dominio (principais entidades da aplicação)
      --interfaces (interfaces gráficas)
         ---elementos (elementos gráficos comuns a muitos menus)
         ---modelosTabela (modelos de tabela customizados para cada menu)
      --persistencia (entidades responsáveis pela persistencia de dados)
      --dados (onde os dados são armazenados e lidos)

3. **Versão do JAVA utilizada:**  
   `jdk Java 24 `
---

## Vídeo de Demonstração

- [Inserir o link para o vídeo no YouTube/Drive aqui]

---

## Prints da Execução

1. Menu Inicial:  
   ![Print Menu Inicial](https://prnt.sc/W56mpDrG_lrJ)

2. Menu Médico:  
   ![Menu Médico](https://prnt.sc/ORrwW9O989B-)
   ![Opções de Cadastro](https://prnt.sc/bGu-0aJ57Q_u)
   ![Cadastro Médico](https://prnt.sc/6oNoNWe0KsJX)
   ![Cadastro Especialidade](https://prnt.sc/WYuqGf8HC-cF)
   ![Relatório "Ver"](https://prnt.sc/CLMzxH-6zpzM)

3. Menu Paciente:
   ![Menu Paciente](https://prnt.sc/2TOw78h635j4)
   ![Cadastro Paciente](https://prnt.sc/I0RKm5xo7ths)
   ![Menu PacienteEspecial (Apertando o botão alternar)](https://prnt.sc/NfkbNv-yyIyF)
   ![Opções de Relatório "Ver"](https://prnt.sc/U7zZG0ACp35F)
   ![Relatório Internações](https://prnt.sc/nAKom-vEAKXm)
   ![Relatório Consultas](https://prnt.sc/w1SoKehtfvOT)

4. Menu Geral
   ![Menu Geral](https://prnt.sc/BwmfKaX0pybp)

5. Menu Consulta
   ![Menu Consulta](https://prnt.sc/5k9o67XDSPiL)
   ![Cadastro Consulta](https://prnt.sc/Vf4pVJbL72za)

6. Menu Internações
   ![Menu Internações](https://prnt.sc/Ao6U0uLiyRlp)
   ![Cadastro Internação](https://prnt.sc/ZBIqcwfiGB08)

7. Cadastro Plano de Saúde
   ![Menu Plano de Saúde](https://prnt.sc/I0_wS9VFxsJE)
   ![Cadastro Plano de Saúde](https://prnt.sc/WwOqiMLHjgOE)

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
