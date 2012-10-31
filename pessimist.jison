%lex
%%

\s+                              /* skip whitespace */
[A-Za-z0-9][A-Za-z0-9-_]+        return 'LITERAL'
[A-Za-z0-9]                      return 'SINGLE_LITERAL'
'='                              return '='
'--'                             return '--'
'-'                              return '-'
[\w0-9_\-\./]+                    return 'VALUE'
<<EOF>>                          return 'EOF'
.                                return 'INVALID'

/lex


%start argv

%%

argv
    : src EOF
        {{
           console.log($1);
           return $1;
        }}
    ;

src 
    :
    | opts
       {{ $$ = {global: $1, commands: undefined}; }}
    | commands
       {{ $$ = {global: undefined, commands: $1}; }}
    | opts commands
       {{ $$ = {global: $1, commands: $2}; }}
    ;

opts
    : opt
       { $$ = [$1]; }
    | opts opt
       {{ $$ = $1.concat([$2]); }}
    ;

opt
    : '-' SINGLE_LITERAL
       {{ $$ = {opt: $2, val: true}; }}
    | '--' LITERAL '=' literal_or_value
       {{ $$ = {opt: $2, val: $4}; }}
    ;

literal_or_value
    : LITERAL
       { $$ = $1; }
    | VALUE
       { $$ = $1; }
    ;

commands
    : command
       {{ $$ = [$1]; }}
    | commands command
       {{ $$ = ($1).concat($2); }}
    ;

command
    : cmd
       {{ $$ = {cmd: $1, opts: []}; }}
    | cmd opts
       {{ $$ = {cmd: $1, opts: $2}; }}
    ;

cmd : LITERAL
       {{ $$ = $1; }}
    ;
