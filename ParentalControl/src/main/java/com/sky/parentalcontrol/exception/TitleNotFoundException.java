package com.sky.parentalcontrol.exception;


/**
 * The Class TitleNotFoundException.
 */
public class TitleNotFoundException extends Exception
{
    private static final long serialVersionUID = 2416831849550380155L;

    private String msg;

    public TitleNotFoundException(final String msg)
    {
        super(msg);
    }

    public TitleNotFoundException(final String msg, final Throwable cause)
    {
        super(msg, cause);
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(final String msg)
    {
        this.msg = msg;
    }
}
