package com.example.Labo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bdexercices";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS exercices (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "img TEXT," +
                "repeat TEXT," +
                "categorie TEXT," +
                "sets TEXT," +
                "duree TEXT," +
                "description TEXT," +
                "pause TEXT," +
                "favorite TEXT)";
        db.execSQL(sql);

        //String[] listDonne = [];
        String[][] databaseData = {
                {"Curl Zottman", "avant_bras_curl_zottman", "x10", "Avant Bras", "3 Set", "5 Minutes", "La position de départ est la même que pour le curl haltères : debout, dos bien droit, genoux légèrement fléchis, deux haltères courtes dans les mains avec une prise en supination. \n\nEn contractant les biceps et en gardant les coudes près du corps, amener les deux haltères en position haute. Une fois dans cette position, faites tourner votre poignet de 180 degrés jusqu'à ce que vous ayez une prise en pronation. Redescendre ensuite les haltères en conservant cette prise. Quand les haltères approchent des cuisses, tournez de nouveau les poignets pour revenir à la position de départ prise en supination.", "30 Secondes", "0"},
                {"Extensions des poignets à la barre", "avant_bras_extensions_des__poignets_la_barre", "x10", "Avant Bras", "3 Set", "5 Minutes", "Saisissez-vous d'une barre, mains en pronation, c'est-à-dire pouces orientés l'un vers l'autre, et asseyez-vous sur un banc, les avant-bras sur les cuisses. \n\nSinon, agenouillez-vous devant un banc en posant vos avant-bras dessus. Vos mains doivent pendre dans le vide. Levez les mains pour soulever la charge sans bouger les bras ni décoller les coudes du support. Maintenez la contraction pendant au moins une seconde puis, redescendez les mains doucement.", "30 Secondes", "0"},
                {"Extensions des poignets aux haltères", "avant_bras_extensions_des_poignets_aux_halteres", "x10", "Avant Bras", "3 Set", "5 Minutes", "Empoignez des haltères et asseyez-vous au bout d'un banc, bras sur les cuisses. Vous pouvez également vous agenouiller devant le banc et placez vos bras sur celui-ci. \n\nVos mains doivent être en pronation, c'est-à-dire avec la paume de main en direction du sol. Elles doivent également pendre dans le vide, au bout de votre genou ou du banc, le cas échéant. Levez les mains, pour soulever les haltères vers le haut, sans décoller le coude du support. Seuls les poignets se plient.", "30 Secondes", "0"},
                {"Flexions des poignets avec haltères", "avant_bras_flexions_des_poignets_avec_halteres", "x10", "Avant Bras", "3 Set", "5 Minutes", "Les flexions des poignets aux haltères se pratiquent de préférence sur un banc, de façon à ce que vos avant-bras ne glissent pas. \n\nSaisissez un haltère dans chaque main (ou dans une seule main si vous choisissez de travailler en unilatéral) et asseyez-vous sur un pupitre ou devant un banc. Vos mains, tournées vers le haut et pouces à l'extérieur, sont suspendues au-dessus du vide, au bord du support. Positionnez-vous légèrement en arrière de façon à ce que vos bras soient relativement tendus.", "30 Secondes", "0"},

                {"Le curl Araignée", "biceps_intro_curl_araignee", "x10", "Biceps", "3 Set", "5 Minutes", "Se mettre du côté apposé au pupitre. Attrapez la barre, mains en supination. Positionnez vos bras sur le pupitre à 90 degrés. A la force des biceps, amener la barre jusqu'au niveau des épaules puis revenir lentement à la position de départ. \n\nExpirez lors de la contraction et inspirez en revenant à la position bras tendus.", "30 Secondes", "0"},
                {"Curl barre", "biceps_intro_curl_barre", "x10", "Biceps", "3 Set", "5 Minutes", "En position de départ debout, le dos immobile et droit, les genoux fléchis ou une jambe avancée pour éviter de tricher en s'aidant de l'élan et les coudes prés du corps. Monter et descendre la barre sans à-coups. \n\nVous pouvez varier l'écartement des mains en utilisant une prise large, moyenne ou serrée.", "30 Secondes", "0"},
                {"Curl concentration", "biceps_intro_curl_concentration", "x10", "Biceps", "3 Set", "5 Minutes", "Assis sur un banc ou une chaise, le dos droit et légèrement penché en avant, le bras perpendiculaire au sol, le coude appuyé sur l'intérieur de la cuisse près du genou et la main en pronation qui tient l'haltère. \n\nMonter la charge avec le biceps sans tricher en se balançant en arrière, et sans bouger les jambes. Terminer le mouvement main en supination (paume des mains vers soi). Il faut travailler un bras après l'autre.", "30 Secondes", "0"},
                {"Le curl Gironda", "biceps_intro_curl_gironda", "x10", "Biceps", "3 Set", "5 Minutes", "Malgré son nom, le curl Gironda n'est pas vraiment un curl. En effet, on ne réalise pas d'arcs de cercle comme avec un curl barre ; on doit lever la barre verticalement de haut en bas. \n\nPour réaliser l'exercice correctement, il faut tirer la barre à la force des biceps en essayant de la garder contre le buste et bien maintenir les bras en arrière. On utilisera une barre droite en prenant une prise standard de la largeur des épaules.", "30 Secondes", "0"},

                {"Abducteurs assis à la machine (fessiers)", "fessiers_abducteurs_assis_a_la_machine1", "x10", "Cuisses-Fessiers", "3 Set", "5 Minutes", "Chargez la machine selon vos performances puis asseyez-vous sur son siège. Placez vos pieds sur les repose-pieds et l'extérieur de vos cuisses contre les supports prévus à cet effet. \n\nEnfin, saisissez les poignées qui se trouvent de chaque côté de l'appareil et redressez le torse.", "30 Secondes", "0"},
                {"Adducteurs assis à la machine (intérieur cuisses)", "fessiers_adducteurs_assis_a_la_machine2", "x10", "Cuisses-Fessiers", "3 Set", "5 Minutes", "Commencez par choisir la charge adaptée à vos performances. Puis, asseyez-vous sur le siège de la machine et placez vos pieds sur les repose-pieds. \n\nLa partie interne de vos cuisses doit toucher le support prévu à cet effet. Enfin, pour finir votre préparation, saisissez les poignées situées de chaque côté de l'appareil et redressez le torse.", "30 Secondes", "0"},
                {"Les fentes", "fessiers_fentes_avec_halt_res", "x10", "Cuisses-Fessiers", "3 Set", "5 Minutes", "Debout, avancez le pied droit assez loin de sorte qu'en position finale l'angle au niveau du genou droit soit de 90°. Le genou arrière ne touche pas le sol et ne doit pas dépasser l'axe du talon. \n\nPrenez appui sur votre talon et poussez pour revenir à la position de départ. Durant l'exécution du mouvement, regardez en face de vous et conservez le dos bien droit. Soufflez en poussant avec votre jambe. Alternez les cuisses.", "30 Secondes", "0"},
                {"Front Squat", "fessiers_front_squat_barre", "x10", "Cuisses-Fessiers", "3 Set", "5 Minutes", "Commencez par placer la barre sur un rack à squat et par la charger. Réglez la hauteur de barre un peu plus basse que celle de vos épaules. Puis, approchez-vous de la barre de façon à la coller contre votre cou et placez vos mains soit sous la barre, soit dessus, en croisant les bras. \n\nDécollez la barre du rack et reculez de quelques pas. Positionnez-vous debout, avec les pieds à un écartement de la largeur des épaules. La pointe de vos pieds doit être légèrement pointée vers l'extérieur.", "30 Secondes", "0"},

                {"Tirage nuque", "dos_intro_conseils_musculation", "x10", "Dos", "3 Set", "5 Minutes", "S'asseoir sur le siège de la machine en mettant les genoux sous les boudins. Tenir la barre en prise large, les mains en pronation. Tirez la barre vers le bas jusqu'à la nuque, puis revenir lentement à la position de départ. Le dos reste toujours droit pendant l'exécution.", "30 Secondes", "0"},
                {"Muscle-Up", "dos_intro_muscle_up", "x10", "Dos", "3 Set", "5 Minutes", "Le Muscle-up se décompose en 3 parties : la traction, en tirant le corps derrière et non sous la barre ; la transition, où l'on fait passer le corps au dessus de la barre et pour finaliser le mouvement, la répulsion ou dips sur barre fixe. On peut également réaliser l'exercice avec des anneaux de gymnastique, si vous en avez sous la main.", "30 Secondes", "0"},
                {"Le Power Clean", "dos_intro_power_clean", "x10", "Dos", "3 Set", "5 Minutes", "On commence l'exercice dans la position du soulevé de terre, et on amène la barre du sol aux genoux. Le début du mouvement ressemble à un soulevé de terre et se réalise lentement. Ensuite, on amène la barre du milieu des cuisses aux épaules en utilisant la force des hanches. \n\nCette partie se réalise de façon explosive. Il est important de ne pas tirer avec les bras ; ils ne servent qu'à maintenir la barre, pas à l'accélérer. Plus vous pratiquerez, meilleure sera la technique.", "30 Secondes", "0"},
                {"Tirage poitrine", "dos_intro_tirage_devant", "x10", "Dos", "3 Set", "5 Minutes", "Debout, prendre la barre de traction avec une prise plus grande que la largeur d'épaules. Descendre la barre et s'asseoir sur le siège de la machine en mettant les genoux sous les boudins. Pencher le buste un peu en arrière en cambrant le bas du dos et amener la barre au dessus de la poitrine en contractant fortement les dorsaux. Revenir lentement à la position de départ.", "30 Secondes", "0"},

                {"Développé haltère", "epaules_developpe_avec_halteres", "x10", "Épaules", "3 Set", "5 Minutes", "Debout, les deux haltères à la main, s'asseoir en posant les haltères sur les cuisses. Cela permet d'éviter le les ramasser à terre avec tous les risques que cela comporte. \n\nPosition de départ assis sur le banc, les pieds bien écartés, les haltères au niveau des épaules avec les mains en pronation. Développer les haltères puis revenir lentement à la position de départ, en freinant la descente.", "30 Secondes", "0"},
                {"Développé devant", "epaules_developpe_devant", "x10", "Épaules", "3 Set", "5 Minutes", "Position de départ assis sur un banc légèrement incliné. Sortir la barre droite des supports, seul ou avec l'aide d'un partenaire, les mains en pronation bien écartées d'une distance supérieur à la largeur des épaules. \n\nAmener la barre au dessus de la poitrine en freinant la descente puis remonter sans verrouiller les articulations au sommet. Le dos et les lombaires restent collés au banc pendant l'exercice, il ne faut pas trop cambrer.", "30 Secondes", "0"},
                {"Développé nuque", "epaules_developpe_nuque", "x10", "Épaules", "3 Set", "5 Minutes", "Position de départ, assis sur un banc droit. Sortir la barre droite des supports, seul ou avec l'aide d'un partenaire. Les mains sont en pronation, bien écartées, d'une distance supérieure à la largeur des épaules. \n\nAmener la barre derrière la tête, jusqu'au niveau des oreilles, en freinant la descente. Remonter la barre sans verrouiller les articulations au sommet. Le dos et les lombaires restent collés au banc pendant l'exercice pour éviter de trop cambrer. Si vous ressentez trop d'inconfort au niveau des épaules, que vous manquez de souplesse, préférez la version barre devant.", "30 Secondes", "0"},
                {"Élévations latérales à la machine", "epaules_elevations_laterales", "x10", "Épaules", "3 Set", "5 Minutes", "Premièrement, asseyez-vous sur le siège et réglez la machine selon votre morphologie. L'axe de rotation de vos épaules doit se retrouver dans l'alignement de l'axe de rotation de la machine. \n\nPuis, sélectionnez votre charge et positionnez vos bras sous les supports. Vos pieds doivent reposer à plat, sur le sol, et votre buste doit rester droit. Soulevez les supports en levant latéralement les bras jusqu'à l'horizontale. Enfin, retournez à la position de départ en contrôlant le mouvement.", "30 Secondes", "0"},

                {"Mollets au donkey", "mollet_intro_donkey", "x10", "Mollets", "3 Set", "5 Minutes", "Placez vos pieds sur la calle, buste penché en avant et parallèle au sol. A la force des mollets et sans plier vos genoux, monter et descendre lentement la plateforme - ou votre partenaire - qui doit reposer sur vos fesses.", "30 Secondes", "0"},
                {"Mollets debout", "mollet_intro_mollets", "x10", "Mollets", "3 Set", "5 Minutes", "On peut réaliser cet exercice de musculation debout sur une seule jambe ou sur une machine spécifique pour les mollets. Debout, maintenir l'équilibre avec une main, la pointe du pied sur la cale. \n\nMonter le plus haut possible en contractant fort et redescendre le plus bas possible. Ne pas plier le genou pour donner une impulsion. Eviter également de travailler les jambes complètement tendues. Si nécessaire, utiliser un lest pour ajouter de la difficulté à l'exercice.", "30 Secondes", "0"},
                {"Mollets assis", "mollet_intro_mollets_assis", "x10", "Mollets", "3 Set", "5 Minutes", "Sur la machine à soléaire, assis, les pieds sur le support et les talons dans le vide, les manchons au niveau du bas des cuisses. Monter et descendre lentement la charge, avec une amplitude maximale. \n\nAvec un haltère, assis sur un banc, poser la charge sur un genoux en utilisant un tapis ou une serviette comme protection. Placez le pied perpendiculaire à la calle de sorte à avoir un bon appui. Descendre lentement la charge en freinant la descente puis remonter la en contractant fort en position haute.", "30 Secondes", "0"},
                {"Mollets presse à cuisses", "mollet_intro_mollets_presse", "x10", "Mollets", "3 Set", "5 Minutes", "Régler d'abord la charge de départ sur la machine puis se placer sur la presse à cuisses, le dos et les fesses bien calés contre le dossier. Remonter la plate-forme si elle est trop basse à la force des cuisses, en poussant sur vos genoux avec les mains si la charge est trop lourde. \n\nLa charge repose sur la pointe des pieds qui sont écartés de la largeur du bassin, les jambes sont pratiquement tendues. Monter et descendre lentement la plateforme à la force des mollets, sans plier vos genoux.", "30 Secondes", "0"},

                {"Développé assis à la machine", "pectoraux_developpe_assis_a_la_machine", "x10", "Pectoraux", "3 Set", "5 Minutes", "Réglez le siège à votre convenance. Puis, asseyez-vous sur le siège, dos plaqué contre le dossier. Saisissez les poignets avec les mains en pronation. \n\nPoussez les poignets en avant, sans décoller le dos du dossier, jusqu'à ce que vos bras soient tendus. Marquez un arrêt pendant au moins une seconde, puis contrôlez les poignets jusqu'au retour à la position de départ. Recommencez jusqu'à la fin de votre série.", "30 Secondes", "0"},
                {"Développé couché à la smith machine", "pectoraux_developpe_couche_a_la_smith_machine", "x10", "Pectoraux", "3 Set", "5 Minutes", "L'exécution est très similaire à un développé couché à la barre. Placez un banc sous la barre guidée. Chargez celle-ci avec le poids qui vous convient. \n\nPuis, allongez-vous sur le banc, tête sous la barre, avec le dos, les fessiers, les épaules et la tête plaqués contre le banc. Vos pieds doivent reposer sur le sol, les jambes à 90 degrés. Saisissez la barre guidée avec un écartement des mains légèrement plus grand que celui des épaules. Vos mains sont en pronation, donc le dos de vos mains est tourné dans votre direction. Décrochez la barre de sa sécurité et contrôlez sa descente jusqu'au milieu de votre poitrine. Puis, revenez à la position de départ en poussant la barre. Recommencez jusqu'à la fin de la série puis verrouillez de nouveau la barre.", "30 Secondes", "0"},
                {"Développé couché haltères", "pectoraux_developpe_couche_aux_halteres", "x10", "Pectoraux", "3 Set", "5 Minutes", "Saisissez les haltères en pronation. Allongez-vous sur un banc à plat, dos plaqué sur le dossier, et posez les pieds au sol. \n\nPositionnez-vous avec les haltères de chaque côté du torse, serrez les omoplates pour que vos épaules se placent en arrière. Elles ne doivent pas avancer durant le mouvement.", "30 Secondes", "0"},
                {"Développé couché barre", "pectoraux_developpe_couche_barre", "x10", "Pectoraux", "3 Set", "5 Minutes", "Position de départ allongé sur le banc de développé couché. La barre posée sur les supports se trouve au niveau des yeux. Placer les mains sur la barre, en pronation, écartées d'une distance légèrement supérieur à la largeur des épaules. \n\nFléchir les jambes et les rabattre pour avoir les lombaires bien collés au banc et eviter de cambrer. Vous pouvez aussi poser les pieds sur le bord du banc ou à plat sur le sol pour plus d'équilibre mais ne levez pas les fesses en poussant.", "30 Secondes", "0"},

                {"Barre front", "triceps_barre_front", "x10", "Triceps", "3 Set", "5 Minutes", "Position de départ allongé sur le sol ou sur un banc, les bras tendus. Les jambes peuvent être bloquées ou fléchies sur la poitrine pour éviter de cambrer trop le bas du dos. Prendre une barre droite ou coudée, prise de la largeur des épaules, les mains en pronation. \n\nAmener la barre au-dessus de la tête en tendant les bras. Ensuite, fléchir les bras jusqu'à effleurer le front avec la barre, en veillant à garder les coudes immobiles et les bras serrés (les bras ne doivent pas s'écarter l'un de l'autre). Pousser presque jusqu'à l'extension complète.", "30 Secondes", "0"},
                {"Développé couché prise serrée", "triceps_developpe_couche_prise_serree", "x10", "Triceps", "3 Set", "5 Minutes", "La technique d'exécution est assez proche d'un développé couché normal. Le but étant de descendre la barre en pliant les coudes, puis de la remonter bras tendus. Commencez par charger la barre selon vos capacités. \n\nllongez-vous sur le banc, dos à plat, omoplates serrées et épaules en arrière. Posez vos pieds à plat sur le sol. Saisissez la barre, les mains écartées d'une distance plus courte que celle qui sépare vos épaules. Puis, décrochez la barre et descendez-la, en maîtrisant le mouvement, jusqu'à toucher vos pectoraux, puis poussez la barre, en contractant les triceps et les pectoraux, de façon à retrouver la position de départ, bras tendus.", "30 Secondes", "0"},
                {"Extensions à la poulie haute", "triceps_extensions_a_la_poulie_haute", "x10", "Triceps", "3 Set", "5 Minutes", "Position de départ debout, les pieds écartés de la largeur du bassin, le dos bien droit et les genoux fléchis. Les deux mains agrippées à une barre reliée à une poulie haute, les bras serrés contre le buste. \n\nTendre les bras jusqu'à l'extension complète puis revenir à la position de départ. Seul l'avant-bras doit bouger pendant l'exécution de l'exercice et les coudes doivent rester collés aux flancs.", "30 Secondes", "0"},
                {"Extensions au-dessus de la tête", "triceps_extensions_au_dessus_de_la_tete", "x10", "Triceps", "3 Set", "5 Minutes", "Position de départ assis sur un banc, le dos bien droit, un haltère dans la main en position « marteau » et le bras tendu au-dessus de la tête. \n\nDescendre l'haltère derrière la tête en gardant le coude pointé vers le plafond (bras vertical), le plus bas possible et sans heurter le cou votre cou. \n\nRemonter la charge et arrêter l'extension avant de tendre complètement le bras. Seul l'avant-bras doit bouger pendant l'exécution du mouvement, de sorte à se concentrer sur le triceps.", "30 Secondes", "0"},

                {"Crunch à la poulie haute", "abdominaux_crunch_a_la_cordee", "x10", "Abdominaux", "3 Set", "5 Minutes", "Il s'agit de s'agenouiller dos à la poulie haute et de poser les fesses sur les talons. Puis, vous devez saisir chaque bout de la corde dans une main, derrière votre tête. \n\nEnsuite, il faut enrouler le buste vers l'avant pour déplacer la charge en conservant le ventre rentré. \n\nEnfin, revenez à la position de départ en contrôlant la charge.", "30 Secondes", "0"},
                {"Crunch au sol", "abdominaux_crunch_au_sol", "x10", "Abdominaux", "3 Set", "5 Minutes", "Position de départ allongé sur le sol ou sur un banc. Les mains peuvent être placées sur la tête au niveau des tempes, sur la poitrine, ou encore le long du corps (plus facile). Evitez de les positionner derrière la nuque. \n\nLes pieds peuvent être posés sur le sol, près des fesses, ou reposer sur un banc. On peut aussi placer les cuisses à la verticale, genoux fléchis et écartés, pieds croisés, de sorte à ne pas cambrer le bas du dos lors du mouvement. Attention, plus les jambes sont surélevées voire tendues, plus la difficulté augmente. Enrouler le buste vers l'avant en contractant les abdominaux. Les épaules ne décollent que de quelques centimètres du sol, et le bas du dos et les hanches restent fixes.", "30 Secondes", "0"},
                {"Flexions du buste à la machine", "abdominaux_flexions_du_buste_a_la_machine", "x10", "Abdominaux", "3 Set", "5 Minutes", "Commencez par charger la machine, si cela est nécessaire. Puis, réglez le dossier à votre taille et asseyez-vous sur la machine. Collez votre dos au dossier, passez les pieds sous les manchons et saisissez les poignées. \n\nPuis, fléchissez le buste pour descendre les épaules en direction du bas-ventre. Contractez vos abdominaux au maximum puis contrôlez le mouvement pour revenir doucement dans la position de départ.", "30 Secondes", "0"},
                {"Relevés de bassin", "abdominaux_releves_de_bassin", "x10", "Abdominaux", "3 Set", "5 Minutes", "Deux variantes légèrement différentes sont possibles. On peut le faire sur banc incliné et également au sol. Couché sur le banc, jambes fléchies, décoller les fessiers en enroulant le bas du dos et amener les genoux vers la poitrine. \n\nRevenir lentement à la position de départ.", "30 Secondes", "0"}
        };

        for (String[] databaseRow : databaseData) {
            //Initialisation de la table
            ContentValues values = new ContentValues();
            values.put("title", databaseRow[0]);
            values.put("img", databaseRow[1]);
            values.put("repeat", databaseRow[2]);
            values.put("categorie", databaseRow[3]);
            values.put("sets", databaseRow[4]);
            values.put("duree", databaseRow[5]);
            values.put("description", databaseRow[6]);
            values.put("pause", databaseRow[7]);
            values.put("favorite", databaseRow[8]);

            db.insert("exercices", null, values);
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS exercices");
        onCreate(db);
    }

    //**************\\
    //  CRUD         \\
    //*******************************************************************************************************************************************

    public ArrayList<Exercice> obtenirToutLesExercices() {

        ArrayList<Exercice> allEntries = new ArrayList<>();

        try (Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM exercices", null)) {

            while (cursor.moveToNext()) {
                Exercice exerciceHolder = new Exercice();

                exerciceHolder.set_id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                exerciceHolder.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                exerciceHolder.setImg(cursor.getString(cursor.getColumnIndexOrThrow("img")));
                exerciceHolder.setRepeat(cursor.getString(cursor.getColumnIndexOrThrow("repeat")));
                exerciceHolder.setCategorie(cursor.getString(cursor.getColumnIndexOrThrow("categorie")));
                exerciceHolder.setSets(cursor.getString(cursor.getColumnIndexOrThrow("sets")));
                exerciceHolder.setDuree(cursor.getString(cursor.getColumnIndexOrThrow("duree")));
                exerciceHolder.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                exerciceHolder.setPause(cursor.getString(cursor.getColumnIndexOrThrow("pause")));
                exerciceHolder.setFavorite(cursor.getString(cursor.getColumnIndexOrThrow("favorite")));

                allEntries.add(exerciceHolder);

            }
        }

        return allEntries;
    }


    public void insertExercice(String title, String img, String repeat, String categorie, String sets, String duree, String description, String pause, String favorite) {

        //Initialisation de la table
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("img", img);
        values.put("repeat", repeat);
        values.put("categorie", categorie);
        values.put("sets", sets);
        values.put("duree", duree);
        values.put("description", description);
        values.put("pause", pause);
        values.put("favorite", favorite);

        this.getWritableDatabase().insert("exercices", null, values);
    }

    public ArrayList<Exercice> obtenirExerciceParCategorie(String categorie) {

        ArrayList<Exercice> allEntriesCategorie = new ArrayList<>();

        try (Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM exercices WHERE categorie = ?", new String[]{categorie})) {

            while (cursor.moveToNext()) {
                Exercice exerciceHolder = new Exercice();

                exerciceHolder.set_id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                exerciceHolder.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                exerciceHolder.setImg(cursor.getString(cursor.getColumnIndexOrThrow("img")));
                exerciceHolder.setRepeat(cursor.getString(cursor.getColumnIndexOrThrow("repeat")));
                exerciceHolder.setCategorie(cursor.getString(cursor.getColumnIndexOrThrow("categorie")));
                exerciceHolder.setSets(cursor.getString(cursor.getColumnIndexOrThrow("sets")));
                exerciceHolder.setDuree(cursor.getString(cursor.getColumnIndexOrThrow("duree")));
                exerciceHolder.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                exerciceHolder.setPause(cursor.getString(cursor.getColumnIndexOrThrow("pause")));
                exerciceHolder.setFavorite(cursor.getString(cursor.getColumnIndexOrThrow("favorite")));

                allEntriesCategorie.add(exerciceHolder);

            }
        }

        return allEntriesCategorie;
    }

    public ArrayList<Exercice> obtenirFavoris() {

        ArrayList<Exercice> allEntriesFavoris = new ArrayList<>();

        try (Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM exercices WHERE favorite = ?", new String[]{"1"})) {

            while (cursor.moveToNext()) {
                Exercice exerciceHolder = new Exercice();

                exerciceHolder.set_id(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                exerciceHolder.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                exerciceHolder.setImg(cursor.getString(cursor.getColumnIndexOrThrow("img")));
                exerciceHolder.setRepeat(cursor.getString(cursor.getColumnIndexOrThrow("repeat")));
                exerciceHolder.setCategorie(cursor.getString(cursor.getColumnIndexOrThrow("categorie")));
                exerciceHolder.setSets(cursor.getString(cursor.getColumnIndexOrThrow("sets")));
                exerciceHolder.setDuree(cursor.getString(cursor.getColumnIndexOrThrow("duree")));
                exerciceHolder.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                exerciceHolder.setPause(cursor.getString(cursor.getColumnIndexOrThrow("pause")));
                exerciceHolder.setFavorite(cursor.getString(cursor.getColumnIndexOrThrow("favorite")));

                allEntriesFavoris.add(exerciceHolder);

            }
        }

        return allEntriesFavoris;
    }

    public void marquerFavorie(int exerciceID) {
        this.getWritableDatabase().execSQL("UPDATE exercices SET favorite = '1' WHERE _id = ?", new Integer[]{exerciceID});

    }

    public void effacerExercice(int ID) {
        this.getWritableDatabase().execSQL("DELETE FROM exercices WHERE _id  = ?", new Integer[]{ID});
    }


}




