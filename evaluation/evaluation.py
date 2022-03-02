# EVALUATION
# Running this file will grab the JSON file containing the experiment results and create the
# plots and tables in the evaluation/out folder.

# built-in imports
import json
import os

# make sure pandas and matplotlib are installed
try:
    import pandas as pd
    from matplotlib import pyplot as plt
    from matplotlib.ticker import MultipleLocator
    from matplotlib import cycler
except ImportError:
    print("ImportError: Missing dependencies. Please install the following python packages:")
    print("pandas, matplotlib")
    exit(1)


# file and path constants
OUT_FOLDER = 'evaluation/out/'
FILE = 'results.json'
METRICS = ['percentageRemaining', 'numIterations']
BOOLEANS = ['terminated', 'alive']

def load(path):
    with open(path, 'r') as f:
        data = json.load(f)
    return data

def filter_df(df, column, values):
    return df[df[column].isin(values)]

def set_plt_params():

    major = 5.0
    minor = 3.0

    plt.style.use('default')
    plt.rcParams['font.size'] = 15
    plt.rcParams['legend.fontsize'] = 18
    plt.rcParams['text.usetex'] = True
    plt.rcParams['xtick.minor.size'] = minor
    plt.rcParams['xtick.major.size'] = major
    plt.rcParams['ytick.minor.size'] = minor
    plt.rcParams['ytick.major.size'] = major
    plt.rcParams['xtick.direction'] = 'in'
    plt.rcParams['ytick.direction'] = 'in'

def create_plot(df: pd.DataFrame, file_name: str, title: str, ycol: str):

    xsize = 15
    ysize = 5

    fig, ax = plt.subplots(figsize=(xsize, ysize))

    ax = df.plot.bar(rot=0, figsize=(xsize, ysize))

    plt.legend(loc='best')
    plt.title(title)
    plt.ylabel(f'${ycol}$', labelpad = 10)
    plt.xticks(rotation = 90)
    plt.savefig(f"{OUT_FOLDER}{file_name}.png", dpi=300, pad_inches=.15, bbox_inches = 'tight')

def get_reindex_indices(values, mode):

    if mode == 'rect':
        opts = ["SMALL", "MEDIUM", "LARGE"]
    else:
        opts = ["TEST", "SMALL", "MEDIUM"]

    indices = []
    worlds = set(values)
    for b in opts:

        if b in worlds:
            indices.append(b)

        ind = []
        i = 1

        while(b + str(i) in worlds):
            string = b + str(i)
            ind.append(string)
            i += 1

        indices.extend(ind)
    return indices


def make_num_iterations_plot(df, mode):
    df_filter = filter_df(df, 'mode', [mode])[['world', 'agent', 'numIterations']].copy()
    indices = get_reindex_indices(df_filter.world.values, mode)
    df_filter = filter_df(df_filter, 'world', indices).copy()

    df2 = df_filter.pivot(index='world', columns='agent', values='numIterations')
    df2 = df2.reindex(indices)

    create_plot(df2, mode + '_num_iterations', "Number of iterations", " ")

def make_perc_remaining_plot(df, mode):
    df_filter = filter_df(df, 'mode', [mode])[['world', 'agent', 'percentageRemaining']].copy()
    indices = get_reindex_indices(df_filter.world.values, mode)
    df_filter = filter_df(df_filter, 'world', indices).copy()

    df2 = df_filter.pivot(index='world', columns='agent', values='percentageRemaining')
    df2 = df2.reindex(indices)
    create_plot(df2, mode + '_perc_remaining', "Percentage remaining", " ")

def table_analysis(df, mode):
    df_filter = filter_df(df, 'mode', [mode])[['world', 'agent'] + BOOLEANS].copy()
    indices = get_reindex_indices(df_filter.world.values, mode)

    df_piv = df_filter.pivot(index='world', columns='agent', values=BOOLEANS)
    df_piv = df_piv.reindex(indices)

    return df_piv


def rank(df):
    df["rank_cost"] = df.groupby("conf")['pathCost'].rank("dense", ascending=True)
    df["rank_count"] = df.groupby("conf")['exploredNodeCount'].rank("dense", ascending=True)
    return df.groupby("algorithm")[['rank_cost', 'rank_count']].agg(['mean', 'median'])


if __name__ == '__main__':
    set_plt_params()
    data = load(OUT_FOLDER + FILE)
    df = pd.DataFrame(data)

    make_num_iterations_plot(df, 'rect')
    make_perc_remaining_plot(df, 'rect')

    make_num_iterations_plot(df, 'tri')
    make_perc_remaining_plot(df, 'tri')

    make_num_iterations_plot(df, 'hex')
    make_perc_remaining_plot(df, 'hex')

    rect_tab = table_analysis(df, 'rect')
    rect_tab.to_csv(OUT_FOLDER + 'rect_tab.csv')

    tri_tab = table_analysis(df, 'tri')
    tri_tab.to_csv(OUT_FOLDER + 'tri_tab.csv')

    hex_tab = table_analysis(df, 'hex')
    hex_tab.to_csv(OUT_FOLDER + 'hex_tab.csv')