/*
Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>

После выполнения команды, кроме команды exit, программа ждёт следующую команду.

Имя – любое слово.
Если введена команда с номером телефона, нужно проверить, что указанный телефон может начинаться с +, затем идут только цифры.
При соответствии введённого номера этому условию – выводим его на экран вместе с именем, используя строковый шаблон.
В противном случае - выводим сообщение об ошибке.
Для команды с электронной почтой делаем то же самое, но с другим шаблоном – для простоты, адрес должен содержать три
последовательности букв, разделённых символами @ и точкой.

Пример команд:
add Tom email tom@example.com
add Tom phone +7876743558
 */

lateinit var n: String
var phoneContact: Map<String, String> = mapOf()
var mailContact: Map<String, String> = mapOf()
var flag: Boolean = true
fun main() {
    println("""Добро пожаловать в записную книжку 
    |Список команд: 
    |exit - выход
    |help - справка
    |add - добавить контакт
    |showPhone - список телефонов
    |showEmail - список почтовы адресов)""")
    while(flag){
        println("Введите команду")
        n = readlnOrNull().toString()
        when (n) {
            "exit" -> {
                flag = false
            }
            "help" -> {
                println("""Список команд: 
    |exit - выход
    |help - справка
    |add - добавить контакт
    |showPhone - список телефонов
    |showEmail - список почтовы адресов)""")
            }
            "add" -> {
                println("""Способ ввода:
    |<Имя> phone <Номер телефона> - Добавить имя человека и номер телефона(только цифры(может начинаться с +)
    |<Имя> email <Адрес электронной почты> - Добавить имя человека и mail(обязательно в адресе содержит @ и .)""")
                n = readlnOrNull().toString()
                addContactPhoneOrMail(n)
            }
            "showPhone" -> {
                println(phoneContact)
            }

            "showEmail" -> {
                println(mailContact)
            }
            else -> {
                println("Некорректный пункт меню! Введите help, для ознакомления с командами")
            }
        }
    }
}
fun addContactPhoneOrMail(n: String) {
    if (n.filter { it == ' ' }.count() == 2) {
        val words = n.split("\\s".toRegex()).toTypedArray()
        if (words[1] == "phone") {
            if (words[2].contains(Regex("""[0-9]+"""))) {
                if ((words[2][0]).toString().contains("""[0-9]""".toRegex()) || (words[2][0]).toString().contains("[+]".toRegex())) {
                    phoneContact += mapOf(words[0] to words[2])
                    println("Контакт добавлен")
                } else {
                    println("Данные введены некорректно. Введите help -> add для уточнения параметров")
                }
            } else {
                println("Данные введены некорректно. Введите help -> add для уточнения параметров")
            }
        } else if (words[1] == "email") {
            if (words[2].contains("""@""".toRegex()) && words[2].contains(""".""".toRegex())) {
                    mailContact += mapOf(words[0] to words[2])
                    println("Контакт добавлен")
                } else {
                println("Данные введены некорректно. Введите help -> add для уточнения параметров")
            }
        } else {
            println("Данные введены некорректно. Введите help -> add для уточнения параметров")
        }
    } else {
        println("Данные введены некорректно. Введите help -> add для уточнения параметров")
    }
}
