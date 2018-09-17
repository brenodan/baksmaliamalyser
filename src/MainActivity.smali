.class public Lcom/example/breno/collusionapp2/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 12
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method

.method private writeToFile(Ljava/lang/String;Landroid/content/Context;)V
    .registers 7
    .param p1, "data"    # Ljava/lang/String;
    .param p2, "context"    # Landroid/content/Context;

    .line 55
    :try_start_0
    new-instance v0, Ljava/io/OutputStreamWriter;

    const-string v1, "config.txt"

    const/4 v2, 0x0

    invoke-virtual {p2, v1, v2}, Landroid/content/Context;->openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/OutputStreamWriter;-><init>(Ljava/io/OutputStream;)V

    .line 56
    .local v0, "outputStreamWriter":Ljava/io/OutputStreamWriter;
    invoke-virtual {v0, p1}, Ljava/io/OutputStreamWriter;->write(Ljava/lang/String;)V

    .line 57
    invoke-virtual {v0}, Ljava/io/OutputStreamWriter;->close()V
    :try_end_12
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_12} :catch_13

    .line 61
    .end local v0    # "outputStreamWriter":Ljava/io/OutputStreamWriter;
    goto :goto_2e

    .line 59
    :catch_13
    move-exception v0

    .line 60
    .local v0, "e":Ljava/io/IOException;
    const-string v1, "Exception"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "File write failed: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/io/IOException;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 62
    .end local v0    # "e":Ljava/io/IOException;
    :goto_2e
    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .registers 9
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .line 16
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 17
    const v0, 0x7f09001c

    invoke-virtual {p0, v0}, Lcom/example/breno/collusionapp2/MainActivity;->setContentView(I)V

    .line 19
    invoke-virtual {p0}, Lcom/example/breno/collusionapp2/MainActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 21
    .local v0, "intent":Landroid/content/Intent;
    invoke-virtual {v0}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v1

    const-string v2, "android.intent.action.SEND"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_82

    .line 24
    const-string v1, ""

    .line 25
    .local v1, "latitude":Ljava/lang/String;
    const-string v2, ""

    .line 29
    .local v2, "longitude":Ljava/lang/String;
    :try_start_1d
    invoke-virtual {v0}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v3

    if-eqz v3, :cond_7d

    .line 31
    invoke-virtual {v0}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v3

    .line 33
    .local v3, "bundle":Landroid/os/Bundle;
    const-string v4, "latitude"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_38

    .line 34
    const-string v4, "latitude"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->get(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    move-object v1, v4

    .line 36
    :cond_38
    const-string v4, "longitude"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->containsKey(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_49

    .line 37
    const-string v4, "longitude"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->get(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    move-object v2, v4

    .line 39
    :cond_49
    const-string v4, "Collusion"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 40
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ":"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {p0}, Lcom/example/breno/collusionapp2/MainActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v5

    invoke-direct {p0, v4, v5}, Lcom/example/breno/collusionapp2/MainActivity;->writeToFile(Ljava/lang/String;Landroid/content/Context;)V
    :try_end_7d
    .catch Ljava/lang/Exception; {:try_start_1d .. :try_end_7d} :catch_7e

    .line 47
    .end local v3    # "bundle":Landroid/os/Bundle;
    :cond_7d
    goto :goto_82

    .line 43
    :catch_7e
    move-exception v3

    .line 45
    .local v3, "e":Ljava/lang/Exception;
    invoke-virtual {v3}, Ljava/lang/Exception;->printStackTrace()V

    .line 49
    .end local v1    # "latitude":Ljava/lang/String;
    .end local v2    # "longitude":Ljava/lang/String;
    .end local v3    # "e":Ljava/lang/Exception;
    :cond_82
    :goto_82
    return-void
.end method