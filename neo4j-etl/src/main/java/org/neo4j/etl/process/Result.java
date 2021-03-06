package org.neo4j.etl.process;

import static java.lang.String.format;

public class Result
{
    public interface Evaluator
    {
        Evaluator FAIL_ON_NON_ZERO_EXIT_VALUE = result -> result.exitValue() == 0;

        Evaluator IGNORE_FAILURES = result -> true;

        boolean isValid( Result result );
    }

    private final int exitValue;
    private final String stdout;
    private final String stderr;
    private final long durationMillis;

    public Result( int exitValue, String stdout, String stderr, long durationMillis )
    {
        this.exitValue = exitValue;
        this.stdout = stdout;
        this.stderr = stderr;
        this.durationMillis = durationMillis;
    }

    public int exitValue()
    {
        return exitValue;
    }

    public String stdout()
    {
        return stdout;
    }

    public String stderr()
    {
        return stderr;
    }

    public long durationMillis()
    {
        return durationMillis;
    }

    @Override
    public String toString()
    {
        return format( "CommandResult { ExitValue: %s, Stdout: '%s', Stderr: '%s', DurationMillis: %s }",
                exitValue, stdout, stderr, durationMillis );
    }
}
