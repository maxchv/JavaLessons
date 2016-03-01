package ua.step.example;

/**
 * 
 * Приостановка потока Thread.sleep
 *
 */
public class Task04
{
    // Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    static Incremenator incrementator; // Объект побочного потока

    public static void main(String[] args)
    {
        incrementator = new Incremenator(); // Создание потока

        System.out.print("Значение = ");

        incrementator.start(); // Запуск потока

        // Троекратное изменение действия инкременатора
        // с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++)
        {
            try
            {
                Thread.sleep(i * 2 * 1000); // Ожидание в течении i*2 сек.
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            incrementator.changeAction(); // Переключение действия
        }

        incrementator.finish(); // Инициация завершения побочного потока
    }
}

class Incremenator extends Thread
{
    // О ключевом слове volatile - чуть ниже
    private volatile boolean mIsIncrement = true;

    private volatile boolean mFinish = false;

    public void changeAction() // Меняет действие на противоположное
    {
        mIsIncrement = !mIsIncrement;
    }

    public void finish() // Инициирует завершение потока
    {
        mFinish = true;
    }

    @Override
    public void run()
    {
        do
        {
            if (!mFinish) // Проверка на необходимость завершения
            {
                if (mIsIncrement)
                    Task04.mValue++; // Инкремент
                else
                    Task04.mValue--; // Декремент

                // Вывод текущего значения переменной
                System.out.print(Task04.mValue + " ");
            }
            else
                return; // Завершение потока

            try
            {
                Thread.sleep(1000); // Приостановка потока на 1 сек.
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } while (true);
    }
}
