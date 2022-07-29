# WorkShop - Usando MongoDB (NoSQL)

Este é um projeto que contém um CRUD simples com o objetivo de entender a diferença entre o paradigma orientado a documentos e relacional. Para aplicar os conceitos do paradigma orientado a documentos, foi realizado algumas consultas com Spring Data e MongoRepository.

## Camadas do projeto
O projeto foi subdividido no padrão de camadas, porém nem todas entidades foram acessadas via DTO conforme a imagem abaixo.

![image](https://user-images.githubusercontent.com/100853329/181855294-65a913f1-c43f-4c43-bbe0-8d25cd946537.png)

## Depedência do MongoDB
Para poder persistir dados no banco foi utilizado a dependência abaixo:

```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

# Seed do banco de dados
Diferentemente do banco relacional, ao invés de comandos SQL para semear o banco de dados, nos bancos NoSQL, somos obrigados a instanciar os objetos que deverão ser armazenados. Abaixo o seed do banco NoSQL:

```
@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		Comment c1 = new Comment(null, "Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		Comment c2 = new Comment(null, "Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		Comment c3 = new Comment(null, "Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
```
## Banco orientado a agregado, modelo de documentos
Abaixo o diagrama de classes usada como parâmetro para produzir este projeto:

![image](https://user-images.githubusercontent.com/100853329/181855719-8c24decf-057a-43db-b1d9-6de58699aa16.png)

Abaixo, o modelo de documento que esperamos que seja armazenado:
```
{
    "id": "1001",
    "name": "Maria Brown",
    "email": "maria@gmail.com",
    "posts": ["5001", "5010"]
}
{
    "id": "5001",
    "date": "2018-03-21",
    "title": "Partiu viagem",
    "body": "Vou viajar para São Paulo. Abraços!",
    "author": {
          "id": "1001",
          "name": "Maria Brown"
},
    "comments": [
            {
                "text": "Boa viagem mano!",
                "date": "2018-03-21",
                "author": {
                        "id": "1013",
                        "name": "Alex Green"
                }
            },
            {
                "text": "Aproveite!",
                "date": "2018-03-22",
                "author": {
                        "id": "1027",
                        "name": "Bob Grey"
                }
            }
      ]
}
{
      "id": "5010",
      "date": "2018-03-23",
      "title": "Bom dia",
      "body": "Acordei feliz hoje!",
      "author": {
              "id": "1001",
              "name": "Maria Brown"
      },
      "comments": [
              {
                      "text": "Tenha um ótimo dia!",
                      "date": "2018-03-23",
                      "author": {
                              "id": "1013",
                              "name": "Alex Green"
                      }
             }
      ]
}
```
## Colection do Postman
Abaixo o link da colection do Postman, lembrando que a chave {{host}} deverá ser substituida para o endereço da maquina local caso queira realizar o teste ou deverá ser configurado nas variaveis de ambiente:

Link: https://www.getpostman.com/collections/3ba8a56f6a43c6683027

