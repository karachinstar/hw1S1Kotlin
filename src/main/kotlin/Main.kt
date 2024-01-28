/*
Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>

После выполнения команды, кроме команды exit, программа ждёт следующую команду.

Имя – любое слово.
Если введена команда с номером телефона, нужно проверить, что указанный телефон может начинаться с +, затем идут только цифры. При соответствии введённого номера этому условию – выводим его на экран вместе с именем, используя строковый шаблон. В противном случае - выводим сообщение об ошибке.
Для команды с электронной почтой делаем то же самое, но с другим шаблоном – для простоты, адрес должен содержать три последовательности букв, разделённых символами @ и точкой.

Пример команд:
add Tom email tom@example.com
add Tom phone +7876743558
 */
const val hello = "Добро пожаловать в записную книжку \n " +
        "Список команд: \n exit - выход \n help - справка \n " +
        "add - добавить контакт \n showPhone - список телефонов\n showEmail - список почтовы адресов)"
lateinit var n: String
var phoneContact: Map<String, String> = mapOf()
var mailContact: Map<String, String> = mapOf()
fun main() {
    println(hello)
    menu()
}

fun menu(){
    println("Введите команду")
    n = readlnOrNull().toString()
        when (n) {
            "exit" -> {
            }
            "help" -> {
                println(hello)
                menu()
            }
            "add" -> {
                println("Способ ввода:\n<Имя> phone <Номер телефона> - Добавить имя человека и номер телефона(только цифры(может начинаться с +)\n" +
                        "<Имя> email <Адрес электронной почты> - Добавить имя человека и mail(обязательно в адресе содержит @ и .)")
                n = readlnOrNull().toString()
                addContactPhoneOrMail(n)
            }
            "showPhone" -> {
                println(phoneContact)
                menu()
            }

            "showEmail" -> {
                println(mailContact)
                menu()
            }
            else -> {
                println("Некорректный пункт меню! Введите help, для ознакомления с командами")
                menu()
            }
        }
}

fun addContactPhoneOrMail(n: String) {
    if (n.filter { it == ' ' }.count() == 2) {
        val words = n.split("\\s".toRegex()).toTypedArray()
        if (words[1] == "phone") {
            println(words[2])
            println(words[2][0])
            if (words[2].contains(Regex("""[0-9]+"""))) {
                val phone = words[2]
                println(words[2][0])
                if ((words[2][0]).toString().contains("""[0-9]""".toRegex()) || (words[2][0]).toString().contains("""[+]""".toRegex())) {
                    phoneContact += mapOf(words[0] to words[2])
                    println("Контакт добавлен")
                    menu()
                } else {
                    println("Данные введены некорректно. Введите help -> add для уточнения параметров")
                    menu()
                }
            } else {
                println("Данные введены некорректно. Введите help -> add для уточнения параметров")
                menu()
            }
        } else if (words[1] == "email") {
            if (words[2].contains("""[@]""".toRegex()) && words[2].contains("""[.]""".toRegex())) {
                    mailContact += mapOf(words[0] to words[2])
                    println("Контакт добавлен")
                    menu()
                } else {
                println("Данные введены некорректно. Введите help -> add для уточнения параметров")
                menu()
            }
        } else {
            println("Данные введены некорректно. Введите help -> add для уточнения параметров")
            menu()
        }
    } else {
        println("Данные введены некорректно. Введите help -> add для уточнения параметров")
        menu()
    }
}
